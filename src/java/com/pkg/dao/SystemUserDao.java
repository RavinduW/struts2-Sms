/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.dao;

import com.pkg.models.SystemUser;

/**
 *
 * @author ravindu_c
 */
public interface SystemUserDao {
    
    //method to register students
    public boolean registerStudent(SystemUser su);
    
    //method to system user login
    public boolean loginSystemUser(String username,String password);
}
