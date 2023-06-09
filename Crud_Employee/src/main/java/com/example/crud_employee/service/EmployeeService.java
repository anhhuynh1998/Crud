package com.example.crud_employee.service;

import com.example.crud_employee.dao.EmployeeDAO;
import com.example.crud_employee.dto.Pageable;
import com.example.crud_employee.model.Employee;

import java.util.List;

public class EmployeeService implements BaseCRUDService<Employee>{
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    @Override
    public void create(Employee employee) {
employeeDAO.insertEmployee(employee);
    }

    @Override
    public void update(Employee employee) {
employeeDAO.updateEmployee(employee);
    }

    @Override
    public void delete(int id) {
employeeDAO.deleteEmployee(id);
    }

    @Override
    public Employee findById(int id) {
        return employeeDAO.findById(id);
    }

    @Override
    public List<Employee> findAll(Pageable pageable) {
        return employeeDAO.findALL(pageable);
    }
}
