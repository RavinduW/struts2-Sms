/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.daoImpl;

import com.pkg.dao.SystemUserDao;
import com.pkg.models.SystemUser;
import com.pkg.utils.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ravindu_c
 */
public class SystemUserDaoImpl implements SystemUserDao {
    
    //keep a single copy
    static Connection currentConnection = null;
    
    //member variables
    String message = "";
    boolean success = false;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String query = "";
        
    @Override
    public boolean registerStudent(SystemUser su){
        
        query = "INSERT INTO system_users(username,password,role_id,firstName,lastName,email) VALUES(?,?,?,?,?,?)";
  
        try{
            //get the db connection
            currentConnection = ConnectionManager.getConnection();
            
            ps = currentConnection.prepareStatement(query);
            ps.setString(1,su.getUsername());
            ps.setString(2,su.getPassword());
            ps.setInt(3,su.getRole_id());
            ps.setString(4, su.getFirstName());
            ps.setString(5, su.getLastName());
            ps.setString(6, su.getEmail());
            
            ps.executeUpdate();
            message = "Student was registered successfully!";
            success = true;
            
        }catch(Exception e){
            System.out.println(e);
            success = false;
 
        }finally{
            if(currentConnection != null){
                try{
                    currentConnection.close();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
            
            if(ps != null){
                try{
                    ps.close();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }
        return success;
    }//registerStudent method
    
    @Override
    public boolean loginSystemUser(String username,String password){

        query = "SELECT * FROM system_users WHERE username=? AND password=?";
        
        try{
            
            currentConnection = ConnectionManager.getConnection();
            ps = currentConnection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            
            success = rs.next();
            
        }catch(Exception e){
            System.out.println(e);
            success = false;
        }finally{
            if(currentConnection != null){
                try{
                    currentConnection.close();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
            
            if(ps != null){
                try{
                    ps.close();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
            
            if(rs != null){
                try{
                    rs.close();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }
        
        return success;
    }//loginSystemUser method
    
    @Override
    public List<SystemUser> getSystemUserDetails(String username){
       query = "SELECT * FROM system_users WHERE username=?";
       List <SystemUser> userdetails = new ArrayList<>();

       try{
            currentConnection = ConnectionManager.getConnection();
            ps = currentConnection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            
            while(rs.next()){
               SystemUser su = new SystemUser();
               //set the values in setters
               su.setId(rs.getInt(1));
               su.setUsername(rs.getString(2));
               su.setPassword(rs.getString(3));
               su.setRole_id(rs.getInt(4));
               su.setFirstName(rs.getString(5));
               su.setLastName(rs.getString(6));
               su.setEmail(rs.getString(7));
               
               userdetails.add(su); 
           }

       }catch(Exception e){
           System.out.println(e);
       }finally{
            
           if(currentConnection != null){
                try{
                    currentConnection.close();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
            
            if(ps != null){
                try{
                    ps.close();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
            
            if(rs != null){
                try{
                    rs.close();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
       }
       return userdetails;
    }
    
}
