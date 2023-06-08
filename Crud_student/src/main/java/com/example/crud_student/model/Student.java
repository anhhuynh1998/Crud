package com.example.crud_student.model;

import java.util.Date;

public class Student {
private int id;
private String name;
private Date dob;
private String gender;
private ClassModel className;

    public Student() {
    }

    public Student(int id, String name, Date dob, String gender, ClassModel className) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.className = className;
    }

    public Student(String name, Date dob, String gender, ClassModel className) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ClassModel getClassName() {
        return className;
    }

    public void setClassName(ClassModel className) {
        this.className = className;
    }
}
