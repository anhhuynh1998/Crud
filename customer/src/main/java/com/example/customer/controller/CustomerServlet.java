package com.example.customer.controller;

import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.*;

@WebServlet(name = "customerServlet", value = "/customers")
public class CustomerServlet extends HttpServlet {
    private final CustomerService customerService = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String action = req.getParameter("action");
       if (action == null) {
           action = "";
       }
    switch (action){
        case "add":
            createCustomer(req,resp);
            break;
        case "edit":
            editCustomer(req,resp);
        default:
            showCustomer(req,resp);
    }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action = req.getParameter("action");
    if (action == null){
        action = "";
    }
    switch (action){
        case "add":
            showCreateCustomer(req,resp);
        case "edit":
            showEditCustomer(req,resp);
        case "delete":
            deleteCustomer(req,resp);
        default:
            showCustomer(req,resp);
    }
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void showEditCustomer(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void showCreateCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create.jsp").forward(req,resp);

    }

    private void showCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("customers",customerService.findALL());
        req.getRequestDispatcher("demo.jsp").forward(req,resp);
    }

    private void editCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email= req.getParameter("email");
        Customer customer = new Customer(id,name,email);
        customerService.update(customer);
        req.setAttribute("message","updated");
        req.getRequestDispatcher("edit.jsp").forward(req,resp);

    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        customerService.create(new Customer(name,email));
        req.setAttribute("message","Created");
        req.getRequestDispatcher("create.jsp").forward(req,resp);
    }


}
