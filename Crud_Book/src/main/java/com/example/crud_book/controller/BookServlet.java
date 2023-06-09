package com.example.crud_book.controller;

import com.example.crud_book.model.Book;
import com.example.crud_book.model.Category;
import com.example.crud_book.service.BookService;
import com.example.crud_book.service.CategoryService;

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

@WebServlet(name = "BookServlet",value = "/books")
public class BookServlet extends HttpServlet {
    private final BookService bookService = new BookService();
    private CategoryService categoryService = new CategoryService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                createBook(req, resp);
                break;
            case "edit":
                editBook(req,resp);
                break;
            default:
                showBook(req,resp);
        }

    }

    private void showBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books =bookService.findAll();
        req.setAttribute("books",books);
        req.getRequestDispatcher("demo.jsp").forward(req,resp);
    }

    private void editBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = dateFormat.parse(req.getParameter("date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String author= req.getParameter("author");
        int category_id = Integer.parseInt(req.getParameter("category"));
        Category category = categoryService.findById(category_id);
        Book books = new Book(id,name,date,author, category);
        bookService.update(books);
        req.setAttribute("books",books);
        req.setAttribute("category", categoryService.findAll());
        req.setAttribute("message", "edited");
        req.getRequestDispatcher("edit.jsp").forward(req,resp);
        
    }

    private void createBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = dateFormat.parse(req.getParameter("date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String author= req.getParameter("author");
        int category_id = Integer.parseInt(req.getParameter("category"));
        Category category = categoryService.findById(category_id);
        Book book = new Book(name,date,author, category);
        bookService.create(book);
        req.setAttribute("book",book);
        req.setAttribute("category", categoryService.findAll());
        req.setAttribute("message", "Created");
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
                showFormCreateBook(req, resp);
                break;
            case "edit":
                showEditBook(req, resp);
                break;
            case "delete":
                deleteBook(req, resp);
                break;
            default:
                showBook(req,resp);
        }

    }

    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        bookService.delete(id);
        showBook(req, resp);
    }

    private void showEditBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        // lấy customer có id bằng với id trong parameter;
        // gửi customer qua bên edit.jsp;
        // điều hướng tới trang edit.jsp;
        Book book = bookService.findById(id);
        req.setAttribute("book", book);
        req.setAttribute("category", categoryService.findAll());
        req.getRequestDispatcher("edit.jsp").forward(req, resp);
    }

    private void showFormCreateBook(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        req.setAttribute("category", categoryService.findAll());
        req.getRequestDispatcher("create.jsp")
                .forward(req,resp);
    }
}
