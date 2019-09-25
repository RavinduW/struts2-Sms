/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.dao;

import com.pkg.models.Role;
import java.util.List;

/**
 *
 * @author ravindu_c
 */
public interface RoleDao {
    
    public boolean addRole(Role role);
    
    public int getRole(String role_name);
    
    public List<Role> getAllRoles();
    
}
