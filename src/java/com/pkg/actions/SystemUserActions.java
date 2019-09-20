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

/**
 *
 * @author ravindu_c
 */
public class SystemUserActions extends ActionSupport implements ModelDriven<SystemUser> {
    
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
    
    //there are 2 actions that are binded to the system user... student registration and system user login
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
       //System.out.println(systemuser.getUsername());
       if(sus.login(systemuser.getUsername(), systemuser.getPassword())){
           return SUCCESS;
       }else{
           return ERROR;
       } 
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