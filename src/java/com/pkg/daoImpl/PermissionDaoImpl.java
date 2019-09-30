/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.daoImpl;

import com.pkg.dao.PermissionDao;
import static com.pkg.daoImpl.RoleDaoImpl.currentConnection;
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
public class PermissionDaoImpl implements PermissionDao{

    boolean access = false;
    String query = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    //keep a single copy
    static Connection currentConnection = null;
    
    @Override
    public List<String> checkPermission(String action_name) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String queryGetId = "SELECT id FROM actions WHERE action_name=?";
        query = "SELECT id FROM roles INNER JOIN permissions ON roles.id = permissions.role_id WHERE permissions.action_id = ?";
        int action_id=0;
        List<String> userRoles = new ArrayList<>();
        
        try{
            currentConnection = ConnectionManager.getConnection();
            
            ps = currentConnection.prepareStatement(queryGetId);
            ps.setString(1, action_name);
            rs = ps.executeQuery();
            
            if(rs.next()){
                action_id = rs.getInt("id");
                ps.close();
            }
            
            ps = currentConnection.prepareStatement(query);
            ps.setInt(1, action_id);
            rs = ps.executeQuery();
            
            while(rs.next()){
                userRoles.add(rs.getString("id"));
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
        return userRoles;
    }  
}
    

