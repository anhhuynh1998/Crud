package com.example.demo_product.service;

import java.util.List;

public interface BaseCRUDService <T>{
    void create(T t);
    void update(T t);

    void delete(int id);

    T findById(int id);

    List<T> findAll();
}
