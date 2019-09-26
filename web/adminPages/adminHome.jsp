<%-- 
    Document   : adminHome
    Created on : Sep 23, 2019, 8:18:07 AM
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
        <h1>Students</h1>
        <br/> 
        <a href="<s:url action="viewStudents"/>">View Students</a> 
        <br/>
        <h2>Add new student</h2>
        <s:form action="save"> 
            <s:textfield name="username" label="UserName"></s:textfield>  
            <s:textfield name="firstName" label="First Name"></s:textfield>  
            <s:textfield name="lastName" label="Last Name"></s:textfield>
            <s:textfield name="email" label="Email"></s:textfield>
            <s:password name="password" label="Password"></s:password>
            <s:submit value="Register Student"></s:submit>  
        </s:form>
        
        <a href="<s:url action="logout"/>">LogOut</a>
    </body>
</html>
