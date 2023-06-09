package com.example.crud_employee.model;

import java.util.Date;

public class Employee {
    private int id;
    private String name;
    private Date dob;
    private Gender gender;
    private double salary;
    private Department department;

    public Employee() {
    }

    public Employee(int id, String name, Date dob, Gender gender, double salary, Department department) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.salary = salary;
        this.department = department;
    }

    public Employee(String name, Date dob, Gender gender, double salary, Department department) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.salary = salary;
        this.department = department;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
