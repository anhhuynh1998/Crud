package com.example.crud_student.controller;

import com.example.crud_student.dao.StudentDAO;
import com.example.crud_student.model.ClassModel;
import com.example.crud_student.model.Student;
import com.example.crud_student.service.ClassService;
import com.example.crud_student.service.StudentService;
import jdk.jfr.Category;
import sun.util.calendar.BaseCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/students")
public class StudentServlet extends HttpServlet {
    private final StudentService studentService = new StudentService();
    private ClassService classService = new ClassService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                createStudent(req, resp);
                break;
            case "edit":
                editStudent(req,resp);
                break;
            default:
                showStudent(req,resp);
        }
    }

    private void showStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentService.findAll();
        req.setAttribute("students",students);
        req.getRequestDispatcher("demo.jsp").forward(req,resp);

    }

    private void editStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Date dob = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dob = dateFormat.parse(req.getParameter("dob"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String gender= req.getParameter("gender");
        int class_id = Integer.parseInt(req.getParameter("class"));
        ClassModel classModel = classService.findById(class_id);
        Student student = new Student(id,name,dob,gender, classModel);
        studentService.update(student);
        req.setAttribute("student",student);
        req.setAttribute("classmodel", classService.findAll());
        req.setAttribute("message", "edited");
        req.getRequestDispatcher("edit.jsp").forward(req,resp);
    }

    private void createStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Date dob = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dob = dateFormat.parse(req.getParameter("dob"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String gender= req.getParameter("gender");
        int class_id = Integer.parseInt(req.getParameter("class"));
        ClassModel classModel = classService.findById(class_id);
        Student student = new Student(name,dob,gender, classModel);
        studentService.create(student);
        req.setAttribute("student",student);
        req.setAttribute("message", "Created");
        req.setAttribute("classmodel", classService.findAll());
        req.getRequestDispatcher("create.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showFormCreateStudent(req, resp);
                break;
            case "edit":
                showEditStudent(req, resp);
                break;
            case "delete":
                deleteStudent(req, resp);
                break;
            default:
                showStudent(req, resp);
        }
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        studentService.delete(id);
        showStudent(req, resp);
    }

    private void showEditStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        // lấy customer có id bằng với id trong parameter;
        // gửi customer qua bên edit.jsp;
        // điều hướng tới trang edit.jsp;
        Student student = studentService.findById(id);
        req.setAttribute("student", student);
        req.setAttribute("classmodel", classService.findAll());
        req.getRequestDispatcher("edit.jsp").forward(req, resp);
    }

    private void showFormCreateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ClassModel> classModels =  classService.findAll();
        req.setAttribute("classmodel",classModels);
        req.getRequestDispatcher("create.jsp")
                .forward(req,resp);
    }
}
