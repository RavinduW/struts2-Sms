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
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ravindu_c
 */

//this LoginActions class contains action methods for the login and logout functionalities

public class LoginActions extends ActionSupport implements ModelDriven<SystemUser>{
    
    private SystemUser systemuser = new SystemUser();
    private SystemUserService sus = new SystemUserService();
    private List<SystemUser> userDetails = new ArrayList<SystemUser>();
    HttpSession session;
    
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
     
    public String logAdmin(){
        return SUCCESS;
    }
    
    //method to make sessions when login
    public HttpSession makeSession(){
        HttpSession newsession = ServletActionContext.getRequest().getSession();
        
        newsession.setAttribute("userId", systemuser.getUsername());
        newsession.setMaxInactiveInterval(120);
        
        return newsession;
    }
    
    //login() is to invoke systemuser login
    public String login() throws NoSuchAlgorithmException, InvalidKeySpecException{
        
        String result = null;
        
        if((systemuser.getUsername() == null || systemuser.getPassword() == null)){
            
            result = "404";
            
        }else if(sus.login(systemuser.getUsername(), systemuser.getPassword()).equals("studentLogin")){ 
                
               session = makeSession(); 
               systemuser.setUsername("");
               result = "student";
               
        }else if(sus.login(systemuser.getUsername(), systemuser.getPassword()).equals("adminLogin")){

               session = makeSession(); 
               systemuser.setUsername("");
               result = "admin";
               
        }else if(sus.login(systemuser.getUsername(), systemuser.getPassword()).equals("teacherLogin")){

               session = makeSession();
               systemuser.setUsername("");
               result = "teacher";
               
        }else if(sus.login(systemuser.getUsername(), systemuser.getPassword()).equals("superAdminLogin")){

               session = makeSession(); 
               systemuser.setUsername("");
               result = "superAdmin";
        }else{
               result = "404";
           }
        
        return result;
    }
    
    public String logout(){
        ServletActionContext.getRequest().getSession().invalidate();
        return "loggedout";
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
