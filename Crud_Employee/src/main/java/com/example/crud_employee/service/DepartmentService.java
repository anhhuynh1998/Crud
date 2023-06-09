package com.example.crud_employee.service;

import com.example.crud_employee.dao.DepartmentDAO;
import com.example.crud_employee.model.Department;

import java.util.List;

public class DepartmentService {
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    public List<Department> findAll(){
        return departmentDAO.findAll();
    }
    public Department findById(int id){
        return departmentDAO.findById(id);
    }
}
