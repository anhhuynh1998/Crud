package com.example.demo_product.controller;

import com.example.demo_product.model.Category;
import com.example.demo_product.model.Product;
import com.example.demo_product.service.CategoryService;
import com.example.demo_product.service.ProductService;

import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "productServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    private final ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showFormCreateProduct(req, resp);
                break;
            case "edit":
                showEditProduct(req, resp);
                break;
            case "delete":
                deleteProduct(req, resp);
                break;
            default:
                showProduct(req,resp);
        }

    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productService.delete(id);
        showProduct(req, resp);
    }

    private void showEditProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        // lấy customer có id bằng với id trong parameter;
        // gửi customer qua bên edit.jsp;
        // điều hướng tới trang edit.jsp;
        Product product = productService.findById(id);
        req.setAttribute("product", product);
        req.setAttribute("category", categoryService.findAll());
        req.getRequestDispatcher("edit.jsp").forward(req, resp);
    }

    private void showFormCreateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("category", categoryService.findAll());
        req.getRequestDispatcher("create.jsp")
                .forward(req,resp);

    }

    private void showProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        req.setAttribute("products",products);
        req.getRequestDispatcher("demo.jsp").forward(req,resp);
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity= Integer.parseInt(req.getParameter("quantity"));
        int category_id = Integer.parseInt(req.getParameter("category"));
        Category category = categoryService.findById(category_id);
        Product product = new Product(id,name,price,quantity, category);
        productService.update(product);
        req.setAttribute("product",product);
        req.setAttribute("category", categoryService.findAll());
        req.setAttribute("message", "edited");
        req.getRequestDispatcher("edit.jsp").forward(req,resp);

    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int categoryId = Integer.parseInt(req.getParameter("category"));
        Category category = categoryService.findById(categoryId);
        Product product = new Product(name,price,quantity,category);
        productService.create(product);
        req.setAttribute("customer", product);
        req.setAttribute("message", "Created");
        req.setAttribute("category", categoryService.findAll());
        req.getRequestDispatcher("create.jsp").forward(req,resp);

    }
}
