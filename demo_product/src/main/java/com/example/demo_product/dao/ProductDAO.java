package com.example.demo_product.dao;

import com.example.demo_product.model.Category;
import com.example.demo_product.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/product";
    private String jdbcUsername = "root";
    //                            password của mình
    private String jdbcPassword = "nhatanh123456";

    private final String SELECT_PRODUCT = "select p.*,c.name as " +
            "category_name from products p left join category c on p.category_id = c.id";
    private final String SELECT_PRODUCT_BY_ID ="SELECT products.*, category.`name` as category_name " +
            "FROM products LEFT JOIN category " + "ON products.category_id = " +
            "category.id where products.id = ?;";
    private final String INSERT_PRODUCT = "INSERT INTO `products` (`name`, `price`,`quantity`, `category_id`) " +
            "VALUES (?, ?, ?,?);";

    private final String UPDATE_PRODUCT = "UPDATE `products` " +
            "SET `name` = ?, `price` = ?,`quantity` = ?, category_id = ? WHERE (`id` = ?);";

    private final String DELETE_PRODUCT = "DELETE FROM `products` WHERE (`id` = ?);";
    public ProductDAO(){}
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<Product> findALL(){
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_PRODUCT);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String category_name = rs.getString("category_name");
                int category_id = rs.getInt("category_id");
                products.add(new Product(id, name, price,quantity, new Category(category_id, category_name)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }
    public Product findById(int id) {
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_PRODUCT_BY_ID);) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);

            // Step 3: tương đương vowis cái sét
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4:
            //kiểm tra còn data hay không. còn thì cứ lấy bằng câu lệnh ở dưới
            while (rs.next()) {
                //(truyên vào tên cột)
                int idCus = rs.getInt("id");
                //(truyên vào tên cột)
                String name = rs.getString("name");
                //(truyên vào tên cột)
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int category_id = rs.getInt("category_id");
                String category_name = rs.getString("category_name");
                return new Product(idCus, name, price,quantity, new Category(category_id, category_name));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void insertProduct(Product product) {
        System.out.println(INSERT_PRODUCT);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setInt(4, product.getCategory().getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateProduct(Product product) {

        try (Connection connection = getConnection();
             //UPDATE `customers` " +
             //            "SET `name` = ?, `email` = ?, role_id = ? WHERE (`id` = ?);";
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setDouble(3, product.getQuantity());
            preparedStatement.setInt(4, product.getCategory().getId());
            preparedStatement.setInt(5, product.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteProduct(int id) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
