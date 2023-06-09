package com.example.crud_employee.controller;

import com.example.crud_employee.dao.EmployeeDAO;
import com.example.crud_employee.dto.Pageable;
import com.example.crud_employee.model.Department;
import com.example.crud_employee.model.Employee;
import com.example.crud_employee.model.Gender;
import com.example.crud_employee.service.DepartmentService;
import com.example.crud_employee.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "EmployeeServlet",value = {"/employees","/"})
public class EmployeeServlet extends HttpServlet {
    private int TOTAL_ITEMS = 5;
    private final EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showFormCreateEmployee(req, resp);
                break;
            case "edit":
                showEditEmployee(req, resp);
                break;
            case "delete":
                deleteEmployee(req, resp);
                break;
            default:
                showEmployee(req, resp);
        }
    }

    private void showEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");

        int page = 1;
        if(req.getParameter("page") != null){
            page = Integer.parseInt(req.getParameter("page"));
        }
        String sortBy = req.getParameter("sortby");
        if(sortBy == null){
            sortBy = "asc";
        }
        String nameField = req.getParameter("nameField");
        if(nameField == null){
            nameField = "department.id";
        }
        Pageable pageable = new Pageable(search, page, TOTAL_ITEMS , nameField, sortBy);
        List<Employee> employee = employeeService.findAll(pageable);
        req.setAttribute("employee",employee);
        req.setAttribute("pageable", pageable);
        req.getRequestDispatcher("demo.jsp").forward(req,resp);
    }

    private void deleteEmployee(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        employeeService.delete(id);
        showEmployee(req, resp);
    }

    private void showEditEmployee(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        // lấy customer có id bằng với id trong parameter;
        // gửi customer qua bên edit.jsp;
        // điều hướng tới trang edit.jsp;
        Employee employee = employeeService.findById(id);
        req.setAttribute("employee", employee);
        req.setAttribute("genders",Gender.values());
        req.setAttribute("department", departmentService.findAll());
        req.getRequestDispatcher("edit.jsp").forward(req, resp);
        
    }

    private void showFormCreateEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> department =  departmentService.findAll();
        req.setAttribute("department",department);
        req.setAttribute("genders",Gender.values());
        req.getRequestDispatcher("create.jsp")
                .forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                createEmployee(req, resp);
                break;
            case "edit":
                editEmployee(req,resp);
                break;
            default:
                showEmployee(req,resp);
        }
    }

    private void editEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Date dob = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dob = dateFormat.parse(req.getParameter("dob"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Gender gender = Gender.valueOf(req.getParameter("gender"));

        double salary = Double.parseDouble(req.getParameter("salary"));
        int department_id = Integer.parseInt(req.getParameter("department"));
        Department department = departmentService.findById(department_id);
        Employee employee = new Employee(id,name,dob,gender,salary, department);
        employeeService.update(employee);
        req.setAttribute("employee",employee);
        req.setAttribute("department", departmentService.findAll());
        req.setAttribute("message", "edited");
        req.getRequestDispatcher("edit.jsp").forward(req,resp);
    }

    private void createEmployee(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException{
        String name = req.getParameter("name");
        Date dob = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dob = dateFormat.parse(req.getParameter("dob"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Gender gender = Gender.valueOf((req.getParameter("gender")));
        double salary = Double.parseDouble(req.getParameter("salary"));
        int department_id = Integer.parseInt(req.getParameter("department_id"));
        Department department = departmentService.findById(department_id);
        Employee employee = new Employee(name,dob,gender,salary, department);
        employeeService.create(employee);
        req.setAttribute("employee",employee);
        req.setAttribute("message", "Created");
        req.setAttribute("genders",Gender.values());
        req.setAttribute("department", departmentService.findAll());
        req.getRequestDispatcher("create.jsp").forward(req,resp);
    }
}
