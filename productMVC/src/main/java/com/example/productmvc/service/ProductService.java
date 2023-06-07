package com.example.productmvc.service;

import com.example.productmvc.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static List<Product> products ;
    private static int currentId;
    static {
        products = new ArrayList<>();
        products.add(new Product(1,"gà",50,50,"tốt"));
        products.add(new Product(2,"vịt",50,50,"tốt"));
    }

   public List<Product> findAll() {
       return products;
   }

   public void addProduct(Product product){
       products.add(product);
   }

   public Product editProduct(Product product){
       for (Product item:products) {
           if (item.getId()== product.getId()){
               item.setName(product.getName());
               item.setPrice(product.getPrice());
               item.setQuantity(product.getQuantity());
               item.setCategory(product.getCategory());
           }
       }
       return null;
   }
   public Product findId(int id){
       for (Product product:products) {
           if (product.getId()==id ){
               return product;
           }
       }
       return null;
   }
   public Product delete(int id ){
       for (int i = 0; i < products.size(); i++) {
           if (products.get(i).getId()==id){
               return products.remove(i);
           }

       }
       return null;
   }
}
