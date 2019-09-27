/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.actions;

import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pkg.models.SystemUser;
import com.pkg.services.SystemUserService;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ravindu_c
 */
public class SystemUserActions extends ActionSupport implements ModelDriven<SystemUser> {
    
    private SystemUserService sus = new SystemUserService();
    private SystemUser systemuser = new SystemUser();
    private List<SystemUser> studentList;
    private List<SystemUser> student;

    public String adminHome(){
        return SUCCESS;
    }

    public String viewStudents(){
        studentList = sus.getStudentsList();
        return SUCCESS;
    }
    
   // save() is to invoke student registration
    public String addStudents() throws NoSuchAlgorithmException, InvalidKeySpecException{
        if(sus.studentRegistration(getSystemuser())){
            return SUCCESS;
        }else{
            return ERROR;    
        }
    }
    
    public String editStudents(){ //get the editStudents.jsp
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
        //student = sus.getStudent(request.getParameter("username"));
        System.out.println(request.getParameter("username"));
        systemuser = sus.getStudent(request.getParameter("username"));
        return SUCCESS;
    }
    
    public String updateStudents(){ //update student de6tails
        if(sus.updateStudent(systemuser.getUsername(), systemuser)){
            return SUCCESS;
        }else{
            return ERROR;
        }
    }
    
    public String deleteStudents(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
        sus.deleteStudent(request.getParameter("username"));
        return SUCCESS;
    }

    @Override
    public SystemUser getModel() {
        return getSystemuser();
    }

    /**
     * @return the systemuser
     */
    public SystemUser getSystemuser() {
        return systemuser;
    }

    /**
     * @param systemuser the systemuser to set
     */
    public void setSystemuser(SystemUser systemuser) {
        this.systemuser = systemuser;
    }

    /**
     * @return the studentList
     */
    public List<SystemUser> getStudentList() {
        return studentList;
    }

    /**
     * @param studentList the studentList to set
     */
    public void setStudentList(List<SystemUser> studentList) {
        this.studentList = studentList;
    }


}
