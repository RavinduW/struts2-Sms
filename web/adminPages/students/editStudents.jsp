<%-- 
    Document   : editStudents
    Created on : Sep 26, 2019, 4:28:36 PM
    Author     : ravindu_c
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
           @import url(style.css); 
        </style>
        <title>Student Management System</title>
    </head>
    <body>
        <h1>Edit Student</h1>
        <br/>
        <s:form action="updateStudents" method="post"> 
            <s:push value="systemuser">
                <label>Username : </label>
                <br/>
                <input  name="username" value=<s:property value="username"/> ></input>  
                <br/><br/>

                <label>First Name : </label>
                <br/>
                <input name="firstName" value=<s:property value="firstName" />></input> 
                <br/><br/>

                <label>Last Name : </label>
                <br/>
                <input name="lastName" value=<s:property value="lastName" />></input>
                <br/><br/>

                <label>Email : </label>
                <br/>
                <input name="email" value=<s:property value="email" />></input>
                <br/><br/>       
                <s:submit value="Update Student"></s:submit> 
            </s:push>  
 
        </s:form>
    </body>
</html>
