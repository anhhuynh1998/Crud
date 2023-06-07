package com.example.customer.service;

import com.example.customer.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService implements BasCRUDService<Customer>{
    private static List<Customer> customerList;
    private static int currenId;
    static {
        customerList =new ArrayList<>();
        customerList.add(new Customer(++currenId,"vinh","bang@gmail.com"));
        customerList.add(new Customer(++currenId,"bo","bo@gmail.com"));
    }

    @Override
    public void create(Customer customer) {
    customer.setId(++currenId);
    customerList.add(customer);
    }

    @Override
    public void update(Customer customer) {
        for (Customer item:customerList) {
            if (item.getId()== customer.getId()){
            item.setId(customer.getId());
            item.setName(customer.getName());
            item.setEmail(customer.getEmail());

            }
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Customer findById(int id) {
        return customerList.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Customer> findALL() {
        return customerList;
    }
}
