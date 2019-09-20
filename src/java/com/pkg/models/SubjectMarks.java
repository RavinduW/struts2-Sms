/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pkg.models;

/**
 *
 * @author ravindu_c
 */
public class SubjectMarks {
    
    private int sub_id;
    private int stu_id;
    private int marks;

    //getters and setters
    /**
     * @return the sub_id
     */
    public int getSub_id() {
        return sub_id;
    }

    /**
     * @param sub_id the sub_id to set
     */
    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    /**
     * @return the stu_id
     */
    public int getStu_id() {
        return stu_id;
    }

    /**
     * @param stu_id the stu_id to set
     */
    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    /**
     * @return the marks
     */
    public int getMarks() {
        return marks;
    }

    /**
     * @param marks the marks to set
     */
    public void setMarks(int marks) {
        this.marks = marks;
    }
    //end of getters and setters
    
}//class SubjectMarks
