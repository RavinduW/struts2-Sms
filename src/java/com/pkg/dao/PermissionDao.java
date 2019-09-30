/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.dao;

import java.util.List;

/**
 *
 * @author ravindu_c
 */
public interface PermissionDao {
    
    //check if the particular user has the permission to invoke the action
    public List<String> checkPermission(String action_name);
}
