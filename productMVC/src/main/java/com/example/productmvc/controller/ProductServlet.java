package com.example.productmvc.controller;

import com.example.productmvc.model.Product;
import com.example.productmvc.service.CategoryService;
import com.example.productmvc.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private List<Product> products = new ArrayList<>();
    private ProductService productService = new ProductService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                createProduct(req, resp);
                break;
            case "edit":
                editProduct(req,resp);
                break;
            default:
                showProduct(req,resp);
        }
    }

    private void showEditProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findId(id);
        req.setAttribute("product",product);
        req.getRequestDispatcher("edit.jsp").forward(req,resp);

    }

    private void showProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products1 = productService.findAll();
        req.setAttribute("products",products1);
        req.getRequestDispatcher("demo.jsp").forward(req,resp);
    }

    private void showCreateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", CategoryService.categories);
        req.getRequestDispatcher("create.jsp").forward(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreateProduct(req, resp);
                break;
            case "edit":
                showEditProduct(req,resp);
                break;
            case "delete":
                deleteProduct(req, resp);
                break;
            default:
                showProduct(req,resp);
        }
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String category = req.getParameter("category");
        Product product = new Product(id,name,price,quantity,category);
        productService.editProduct(product);
        req.setAttribute("product",product);
        req.setAttribute("message","edited");
        req.getRequestDispatcher("edit.jsp").forward(req,resp);


    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productService.delete(id);
        showProduct(req,resp);
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String category = req.getParameter("category");
        productService.addProduct(new Product(name,price,quantity,category));
        req.setAttribute("message","Created");
        req.getRequestDispatcher("create.jsp").forward(req,resp);
    }
}