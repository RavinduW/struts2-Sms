/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.pkg.models.SystemUser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ravindu_c
 */
public class UserRoleInterceptor extends AbstractInterceptor {

    private List <String> allowedRoles = new ArrayList<String>();
    private List <String> disallowedRoles = new ArrayList<String>();

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        
        String result = ai.invoke();

        if (!isAllowed(request, ai.getAction())) {
            result = reject(ai, response);
        } else {
            result = ai.invoke();
            System.out.println("not rejected");
        }

        return result;
    }
    
    //check the requests are allowed or not
    private boolean isAllowed(HttpServletRequest request,Object action){
        
        HttpSession  session = request.getSession(false);
        //HttpSession session = ServletActionContext.getRequest().getSession();//doesn't creating a new session. just get it
        SystemUser loggedInUser = null;
        boolean result = false;
        
        if(session != null){
            loggedInUser = (SystemUser)session.getAttribute("userId"); //type casted the attribute
            System.out.println("lg user"+loggedInUser);
            System.out.println(session.getAttribute("userId"));
        }
        
        if(getAllowedRoles().size()>0){
            if(session == null || loggedInUser == null){
                System.out.println("session null or lu null");
                return result;
            }
            
            for(int i=0;i<getAllowedRoles().size();i++){
                if(getAllowedRoles().get(i).equalsIgnoreCase(loggedInUser.getRole())){
                    System.out.println("role");
                    result = true;
                }
            }//end of for loop
            return result;
        }else if(getDisallowedRoles().size()>0){
            if(session != null || loggedInUser != null){
                for(int i=0;i<getDisallowedRoles().size();i++){
                    if(getDisallowedRoles().get(i).equalsIgnoreCase(loggedInUser.getRole())){
                        return false;
                    }
                }
            }
        }
            
        return true;
    }
    
//        protected List<String> stringToList(String val) {
//        if (val != null) {
//            String[] list = val.split("[ ]*,[ ]*");
//            return Arrays.asList(list);
//        } else {
//            return Collections.EMPTY_LIST;
//        }
//    }
    
    public String reject(ActionInvocation invocation,HttpServletResponse response){
        return "404";
    }

    //getters and setters for the private arrayLists

        /**
     * @return the allowedRoles
     */
    public List <String> getAllowedRoles() {
        return allowedRoles;
    }

    /**
     * @param allowedRoles the allowedRoles to set
     */
    public void setAllowedRoles(List <String> allowedRoles) {
        this.allowedRoles = allowedRoles;
    }

    /**
     * @return the disallowedRoles
     */
    public List <String> getDisallowedRoles() {
        return disallowedRoles;
    }

    /**
     * @param disallowedRoles the disallowedRoles to set
     */
    public void setDisallowedRoles(List <String> disallowedRoles) {
        this.disallowedRoles = disallowedRoles;
    }
    
    //end of getters and setters


    
}
