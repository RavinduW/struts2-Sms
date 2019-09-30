/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.services;

import com.pkg.dao.RoleDao;
import com.pkg.dao.SystemUserDao;
import com.pkg.daoImpl.RoleDaoImpl;
import com.pkg.daoImpl.SystemUserDaoImpl;
import com.pkg.models.SystemUser;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ravindu_c
 */
public class SystemUserService {
    
    //get the SystemUserDao interface refference
    SystemUserDao sud = new SystemUserDaoImpl();
    
    //get the RoleDao interface reference
    RoleDao rd = new RoleDaoImpl();
    
     //password hashing begins
    private static String generateStrongPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        int iterations = 1000; //number of iterations
        char[] chars = password.toCharArray(); //convert the password in to a character array
        
        //salt is random data
        //for each pw,generate new salt
        byte[] salt = getSalt(); //get the salt
         
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        
        byte[] hash = skf.generateSecret(spec).getEncoded(); //hash value
        
        return iterations + ":" + toHex(salt) + ":" + toHex(hash); //return the hash value
    }
     
    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG"); //PRNG=Pseudo Random Number Generator
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
    
    private static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
    
    private static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);
         
        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();
         
        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }
    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
    
    //end of password hashing
    
    //studentRegistration method
    public boolean studentRegistration(SystemUser systemuser) throws NoSuchAlgorithmException, InvalidKeySpecException{
        
        systemuser.setPassword(generateStrongPasswordHash(systemuser.getPassword()));
        systemuser.setRole_id(rd.getRole("student"));
        return sud.registerStudent(systemuser);
    }//studentRegistration method
    
    //login method for system users
    public String login(String username,String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
        
        String authentication = null;
        
        List<SystemUser> userDetails = sud.getSystemUserDetails(username);
        
        if(!userDetails.isEmpty()){

            if(validatePassword(password,userDetails.get(0).getPassword()) && (username.equals(userDetails.get(0).getUsername())) && rd.getRole("student")== userDetails.get(0).getRole_id()){
                authentication = "studentLogin";
            }else if(validatePassword(password,userDetails.get(0).getPassword()) && (username.equals(userDetails.get(0).getUsername())) && rd.getRole("admin")== userDetails.get(0).getRole_id()){
                authentication = "adminLogin";
            }else if(validatePassword(password,userDetails.get(0).getPassword()) && (username.equals(userDetails.get(0).getUsername())) && rd.getRole("teacher")== userDetails.get(0).getRole_id()){
                authentication = "teacherLogin";
            }else if(validatePassword(password,userDetails.get(0).getPassword()) && (username.equals(userDetails.get(0).getUsername())) && rd.getRole("superAdmin")== userDetails.get(0).getRole_id()){
                authentication = "superAdminLogin";
            }else{
                authentication = "failed";
            }
        }        
        return authentication;      
    }
    
    //retrieve system users who has role of student
    public List<SystemUser> getStudentsList(){
        return sud.getStudents();
    }
    
    //retrieve partcular student data
    public SystemUser getStudent(String username){
        
        List<SystemUser> list = new ArrayList<SystemUser>();
        list = sud.getSystemUserDetails(username);
        
        if(list.get(0).getRole_id()== rd.getRole("student")){
            return list.get(0);
        }
       
        return null;   
    }
    
    //update particular student data
    public boolean updateStudent(String username, SystemUser student){
        boolean result = false;
        
        if(sud.updateStudent(username, student)){
            result = true;
        }
        
        return result;
    }
    
    public void deleteStudent(String username){
        sud.deleteStudent(username);
    }
    
        //retrieve partcular student data
    public SystemUser getSysUser(String username){
        
        List<SystemUser> list = new ArrayList<SystemUser>();
        list = sud.getSystemUserDetails(username);
        
            return list.get(0);  
    }
    
}
