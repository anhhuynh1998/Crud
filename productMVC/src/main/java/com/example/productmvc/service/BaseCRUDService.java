package com.example.productmvc.service;

import java.util.List;

public interface BaseCRUDService <T>{
    void create(T t);
    void update(T t);
    void delete(int id);
    T findByID(int id);
    List<T> findALL();
}
