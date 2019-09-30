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
import com.pkg.dao.PermissionDao;
import com.pkg.dao.RoleDao;
import com.pkg.daoImpl.PermissionDaoImpl;
import com.pkg.daoImpl.RoleDaoImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.apache.struts2.StrutsStatics.HTTP_REQUEST;
import static org.apache.struts2.StrutsStatics.HTTP_RESPONSE;

/**
 *
 * @author ravindu_c
 */
public class SessionInterceptor implements Interceptor{

    private static final String USER = "userId" ;
    private static final String USER_ROLE = "user_role";
    
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
        HttpServletResponse response = (HttpServletResponse) context.get(HTTP_RESPONSE);
	HttpSession session = request.getSession(true);

	// Is there a "user" object stored in the user's HttpSession
	Object user = session.getAttribute(USER);
        Object user_role = session.getAttribute(USER_ROLE);
        PermissionDao pd = new PermissionDaoImpl();
        RoleDao rd = new RoleDaoImpl();
        
        //set a header to cache control
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        
        if(user == null || user_role == null){ //check if user object is exist or not in session object
            //check if the user action class is loginaction class 
            if (ai.getAction().getClass().equals(LoginActions.class)){
                return ai.invoke();
            }
            return "index";
        }else{ //user has the session
                
                if(ai.getAction().getClass().equals(LoginActions.class) || pd.checkPermission(ai.getInvocationContext().getName()).contains(user_role.toString())){
                    return ai.invoke();
                }else{
                    return "unauthorized";
                }
                
            }
        }
}
