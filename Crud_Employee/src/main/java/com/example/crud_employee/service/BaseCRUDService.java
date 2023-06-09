package com.example.crud_employee.service;

import com.example.crud_employee.dto.Pageable;

import java.util.List;

public interface BaseCRUDService <T>{
    void create(T t);
    void update(T t);

    void delete(int id);

    T findById(int id);

    List<T> findAll(Pageable pageable);
}
