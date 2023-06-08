package com.example.crud_student.service;

import com.example.crud_student.dao.ClassDAO;
import com.example.crud_student.model.ClassModel;

import java.util.List;

public class ClassService {
    private ClassDAO classDAO = new ClassDAO();

    public List<ClassModel> findAll(){
        return classDAO.findAll();
    }

    public ClassModel findById(int id){

        return classDAO.findById(id);
    }
}
