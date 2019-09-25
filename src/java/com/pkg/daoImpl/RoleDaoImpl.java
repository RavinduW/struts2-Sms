/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.daoImpl;

import com.pkg.dao.RoleDao;
import static com.pkg.daoImpl.SystemUserDaoImpl.currentConnection;
import com.pkg.models.Role;
import com.pkg.utils.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author ravindu_c
 */
public class RoleDaoImpl implements RoleDao{
    
    //keep a single copy
    static Connection currentConnection = null;
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    String query = "";

    @Override
    public boolean addRole(Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRole(String role_name) {
        int result = 0;
        
        query = "SELECT id FROM roles WHERE role_name=?";
        
        try{
            currentConnection = ConnectionManager.getConnection();
            
            ps = currentConnection.prepareStatement(query);
            ps.setString(1, role_name);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                result = rs.getInt(1);
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
        return result;
    }

    @Override
    public List<Role> getAllRoles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
