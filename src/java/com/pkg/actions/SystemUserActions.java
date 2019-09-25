/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pkg.models.SystemUser;
import com.pkg.services.SystemUserService;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ravindu_c
 */
public class SystemUserActions extends ActionSupport implements ModelDriven<SystemUser>{
    
    private SystemUser systemuser = new SystemUser();
    private SystemUserService sus = new SystemUserService();
    private List<SystemUser> userDetails = new ArrayList<SystemUser>();
    
    @Override
    public SystemUser getModel() {
        return systemuser;
    }

    //getters and setters for the private SystemUser bean object
    /**
     * @return the systemUser
     */
    public SystemUser getSystemuser() {
        return systemuser;
    }

    /**
     * @param systemUser the systemUser to set
     */
    public void setSystemuser(SystemUser systemuser) {
        this.systemuser = systemuser;
    }
   
    //adminHome() is to return the home page of admin
    public String adminHome(){
        System.out.println("adminHome called");
        return SUCCESS;
    }
    
    //studentHome() is to return the home page of student
    public  String studentHome(){
        return SUCCESS;
    }
   
    //save() is to invoke student registration
    public String save() throws NoSuchAlgorithmException, InvalidKeySpecException{
        if(sus.studentRegistration(systemuser)){
            return SUCCESS;
        }else{
            return ERROR;
        }
    }
    
    //login() is to invoke systemuser login
    public String login() throws NoSuchAlgorithmException, InvalidKeySpecException{
         
        String result = null;
        if(sus.login(systemuser.getUsername(), systemuser.getPassword()).equals("studentLogin")){ 
            
           ServletActionContext.getRequest().getSession().setAttribute("userId", systemuser.getUsername());
           //ServletActionContext.getRequest().getSession().setAttribute("userRole", systemuser.getRole());
           
           systemuser.setUsername("");
           
           result = "student";
       }else if(sus.login(systemuser.getUsername(), systemuser.getPassword()).equals("adminLogin")){
           
           ServletActionContext.getRequest().getSession().setAttribute("userId", systemuser.getUsername());
           //ServletActionContext.getRequest().getSession().setAttribute("userRole", systemuser.getRole());
           
           systemuser.setUsername("");
           
           result = "admin";
       }else if(sus.login(systemuser.getUsername(), systemuser.getPassword()).equals("teacherLogin")){
           
           ServletActionContext.getRequest().getSession().setAttribute("userId", systemuser.getUsername());
           //ServletActionContext.getRequest().getSession().setAttribute("userRole", systemuser.getRole());
           
           systemuser.setUsername("");
           
           result = "teacher";
       }else if(sus.login(systemuser.getUsername(), systemuser.getPassword()).equals("superAdminLogin")){
           
           ServletActionContext.getRequest().getSession().setAttribute("userId", systemuser.getUsername());
           //ServletActionContext.getRequest().getSession().setAttribute("userRole", systemuser.getRole());
           
           systemuser.setUsername("");
           result = "superAdmin";
       }else{
           result = "404";
       }
        return result;
    }
    
    public String logOut(){
        ServletActionContext.getRequest().getSession().invalidate();
        return SUCCESS;
    }
    
    /**
     * @return the userDetails
     */
    public List<SystemUser> getUserDetails() {
        return userDetails;
    }

    /**
     * @param userDetails the userDetails to set
     */
    public void setUserDetails(List<SystemUser> userDetails) {
        this.userDetails = userDetails;
    }
    
}
