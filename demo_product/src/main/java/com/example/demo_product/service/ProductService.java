package com.example.demo_product.service;

import com.example.demo_product.dao.ProductDAO;
import com.example.demo_product.model.Product;

import java.util.List;

public class ProductService implements BaseCRUDService<Product> {
    private final ProductDAO productDAO = new ProductDAO();

    @Override
    public void create(Product product) {
        productDAO.insertProduct(product);
    }

    @Override
    public void update(Product product) {
        productDAO.updateProduct(product);
    }

    @Override
    public void delete(int id) {
        productDAO.deleteProduct(id);
    }

    @Override
    public Product findById(int id) {
        return productDAO.findById(id);
    }

    public List<Product> findAll() {
        return  productDAO.findALL();
    }

}
