/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pkg.dao.SystemUserDao;
import com.pkg.daoImpl.SystemUserDaoImpl;

import com.pkg.models.SystemUser;
import com.pkg.services.SystemUserService;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

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
   
    //home() is to return the home page of system user
    public String home(){
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

       //System.out.println(systemuser.getUsername()+"lk");
       if(sus.login(systemuser.getUsername(), systemuser.getPassword())){ 
           //session.put("userId", systemuser.getUsername());
           //System.out.println(systemuser.getUsername());
           ServletActionContext.getRequest().getSession().setAttribute("userId", systemuser.getUsername());
           //System.out.println((String)ServletActionContext.getRequest().getSession().getAttribute("userId"));
           return "success";
       }else{
           //System.out.println("err");
           return "404";
       } 
    }
    
    public String logOut(){
        //session.remove("userId");
        ServletActionContext.getRequest().getSession().invalidate();
        return SUCCESS;
    }
    
    public String loginPage(){
        //session.remove("userId");
        //ServletActionContext.getRequest().getSession().invalidate();
        return SUCCESS ;
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
