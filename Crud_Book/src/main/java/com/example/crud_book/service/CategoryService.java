package com.example.crud_book.service;

import com.example.crud_book.dao.CategoryDAO;
import com.example.crud_book.model.Category;

import java.util.List;

public class CategoryService {
    private CategoryDAO categoryDAO = new CategoryDAO();
    public List<Category> findAll(){
        return categoryDAO.findAll();
    }

    public Category findById(int id){

        return categoryDAO.findById(id);
    }
}
