<%-- 
    Document   : index
    Created on : Sep 19, 2019, 1:36:54 PM
    Author     : ravindu_c
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Management System</title>
    </head>
    <body>
        <h1>Welcome to the Student Management System !</h1>
        <br/>
        <h2>Sign In</h2>
        <br/>
        <s:form action="login"> 
            <s:textfield name="username" label="UserName"></s:textfield>  
            <s:password name="password" label="Password"></s:password>
            <s:submit value="Login"></s:submit>  
        </s:form> 
        <br/>
        <a href="<s:url action="loginPage"/>">LogIn</a>        
        <s:form action="save"> 
            <s:textfield name="username" label="UserName"></s:textfield>  
            <s:textfield name="firstName" label="First Name"></s:textfield>  
            <s:textfield name="lastName" label="Last Name"></s:textfield>
            <s:textfield name="email" label="Email"></s:textfield>
            <s:password name="password" label="Password"></s:password>
            <s:submit value="Register"></s:submit>  
        </s:form> 
    </body>
</html>
