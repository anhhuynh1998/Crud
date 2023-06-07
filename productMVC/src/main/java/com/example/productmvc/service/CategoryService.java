package com.example.productmvc.service;

import com.example.productmvc.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    public static List<Category> categories;

    static {
        categories = new ArrayList<>();
        categories.add(new Category(1, "Ao"));
        categories.add(new Category(2, "Quan"));
    }
}
