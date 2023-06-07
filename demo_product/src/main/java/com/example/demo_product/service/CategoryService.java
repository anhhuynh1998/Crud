package com.example.demo_product.service;

import com.example.demo_product.dao.CategoryDAO;
import com.example.demo_product.model.Category;

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
