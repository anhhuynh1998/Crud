package com.example.crud_book.dao;
import com.example.crud_book.model.Book;
import jdk.jfr.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class BookDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/crud_book";
    private String jdbcUsername = "root";
    //                            password của mình
    private String jdbcPassword = "nhatanh123456";

    private final String SELECT_BOOK = "select p.*,c.name as " +
            "category_name from book p left join category c on p.category_id = c.id";
    private final String SELECT_BOOK_BY_ID ="SELECT book.*, category.`name` as category_name " +
            "FROM book LEFT JOIN category " + "ON book.category_id = " +
            "category.id where book.id = ?;";
    private final String INSERT_BOOK = "INSERT INTO `book` (`name`, `date`,`author`, `category_id`) " +
            "VALUES (?, ?, ?,?);";

    private final String UPDATE_BOOK = "UPDATE `book` " +
            "SET `name` = ?, `date` = ?,`author` = ?, category_id = ? WHERE (`id` = ?);";

    private final String DELETE_BOOK = "DELETE FROM `book` WHERE (`id` = ?);";

    public BookDAO() {
    }
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
    public List<Book> findALL(){
        List<Book> books = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_BOOK);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Date price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String category_name = rs.getString("category_name");
                int category_id = rs.getInt("category_id");
                books.add(new Book(id, name, price,quantity, new Category(category_id, category_name)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }
}
