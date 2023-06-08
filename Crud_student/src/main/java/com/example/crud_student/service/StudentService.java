package com.example.crud_student.service;

import com.example.crud_student.dao.StudentDAO;
import com.example.crud_student.model.Student;

import java.util.List;

public class StudentService implements BaseCRUDService<Student>{
    private final StudentDAO studentDAO = new StudentDAO();
    @Override
    public void create(Student student) {
        studentDAO.insertStudent(student);
    }

    @Override
    public void update(Student student) {
studentDAO.updateStudent(student);
    }

    @Override
    public void delete(int id) {
studentDAO.deleteStudent(id);
    }

    @Override
    public Student findById(int id) {
        return studentDAO.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentDAO.findALL();
    }
}
