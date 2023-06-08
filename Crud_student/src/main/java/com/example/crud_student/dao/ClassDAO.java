package com.example.crud_student.dao;

import com.example.crud_student.model.ClassModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/crud_student";
    private String jdbcUsername = "root";
    //                            password của mình
    private String jdbcPassword = "nhatanh123456";
    private final String SELECT_CLASS = "SELECT * FROM class";
    private final String SELECT_CLASS_BY_ID = "SELECT * FROM class WHERE id = ?";
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public List<ClassModel> findAll(){
        List<ClassModel> categories = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_CLASS)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                categories.add(new ClassModel(id, name));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return categories;
    }
    public ClassModel findById(int id) {
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_CLASS_BY_ID);) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);

            // Step 3: tương đương vowis cái sét
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4:
            //kiểm tra còn data hay không. còn thì cứ lấy bằng câu lệnh ở dưới
            while (rs.next()) {
                int idCus = rs.getInt("id");
                String name = rs.getString("name");

                return new ClassModel(idCus, name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
