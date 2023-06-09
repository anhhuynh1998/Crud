package com.example.crud_book.dao;
import com.example.crud_book.model.Book;
import com.example.crud_book.model.Category;

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
                Date date = rs.getDate("date");
                String author = rs.getString("author");
                String category_name = rs.getString("category_name");
                int category_id = rs.getInt("category_id");
                books.add(new Book(id, name, date,author, new Category(category_id, category_name)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }
    public Book findById(int id) {
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_BOOK_BY_ID);) {
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
                Date date = rs.getDate("date");
                String author = rs.getString("author");
                int category_id = rs.getInt("category_id");
                String category_name = rs.getString("category_name");
                return new Book(idCus, name, date,author, new Category(category_id,category_name));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void insertBook(Book book) {
        System.out.println(INSERT_BOOK);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK)) {
            preparedStatement.setString(1, book.getName());
            java.sql.Date dateSQL = new java.sql.Date(book.getDate().getTime());
            preparedStatement.setDate(2,  dateSQL);
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setInt(4, book.getCategory().getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateBook(Book book) {

        try (Connection connection = getConnection();
             //UPDATE `customers` " +
             //            "SET `name` = ?, `email` = ?, role_id = ? WHERE (`id` = ?);";
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK)) {
            preparedStatement.setString(1, book.getName());
            java.sql.Date dateSQL = new java.sql.Date(book.getDate().getTime());
            preparedStatement.setDate(2,  dateSQL);
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setInt(4, book.getCategory().getId());
            preparedStatement.setInt(5, book.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteBook(int id) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
