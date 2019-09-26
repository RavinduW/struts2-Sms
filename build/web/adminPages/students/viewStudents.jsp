<%-- 
    Document   : viewStudents
    Created on : Sep 26, 2019, 12:05:43 PM
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
        
        <div>
            <table cellpadding="10" > 
                <tr>
                    <th>Id</th>
                    <th>Username</th>
                    <th>FirstName</th>
                    <th>LastName</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>   
                <s:iterator value="studentList">
			<tr>
                            <td><s:property value="id" /></td>
			    <td><s:property value="username" /></td>
			    <td><s:property value="firstName" /></td>
			    <td><s:property value="lastName" /></td>
			    <td><s:property value="email" /></td>
         
                            <td>
                                <s:url id="editURL" action="editStudents">
					<s:param name="username" value="%{username}"></s:param>
				</s:url>
                                <s:a href="%{editURL}">Edit</s:a>
                            </td>
                            <td>
                                <s:url id="deleteURL" action="deleteStudents">
					<s:param name="username" value="%{username}"></s:param>
				</s:url>
                                <s:a href="%{deleteURL}">Delete</s:a>
                            </td>
			</tr>
		</s:iterator>
            </table>
        </div>
    </body>
</html>
