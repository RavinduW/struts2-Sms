/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.interceptors;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.pkg.actions.LoginActions;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static org.apache.struts2.StrutsStatics.HTTP_REQUEST;

/**
 *
 * @author ravindu_c
 */
public class SessionInterceptor implements Interceptor{

    private static final String USER = "userId" ;
    //private static final String USER_ROLE = "userRole";
    
    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String intercept(ActionInvocation ai) throws Exception {

        final ActionContext context = ai.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
		HttpSession session = request.getSession(true);

		// Is there a "user" object stored in the user's HttpSession
		Object user = session.getAttribute(USER);
                
                //Object user_role = session.getAttribute(USER_ROLE);
        if(user == null){ //check if user object is exist or not in session object
            //check if the user action class is loginaction class 
            if (ai.getAction().getClass().equals(LoginActions.class)){
                return ai.invoke();
            }
            return "index";
        }else{
                return ai.invoke();
            }
        }
}
