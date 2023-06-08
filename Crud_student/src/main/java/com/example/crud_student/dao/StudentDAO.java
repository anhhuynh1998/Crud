package com.example.crud_student.dao;

import com.example.crud_student.model.ClassModel;
import com.example.crud_student.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/crud_student";
    private String jdbcUsername = "root";
    //                            password của mình
    private String jdbcPassword = "nhatanh123456";
    private final String SELECT_STUDENT = "select cr.*,s.name as " +
            "class_name from student cr left join class s on cr.class_id = s.id";
    private final String SELECT_STUDENT_BY_ID ="SELECT student.*, class.`name` as class_name " +
            "FROM student LEFT JOIN class " + "ON student.class_id = " +
            "class.id where student.id = ?;";
    private final String INSERT_STUDENT = "INSERT INTO `student` (`name`, `dob`,`gender`, `class_id`) " +
            "VALUES (?, ?, ?,?);";

    private final String UPDATE_STUDENT = "UPDATE `student` " +
            "SET `name` = ?, `dob` = ?,`gender` = ?, class_id = ? WHERE (`id` = ?);";
    private final String DELETE_STUDENT = "DELETE FROM `student` WHERE (`id` = ?);";
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
    public List<Student> findALL(){
        List<Student> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_STUDENT);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Date dob = rs.getDate("dob");
                String gender = rs.getString("gender");
                String class_name = rs.getString("class_name");
                int class_id = rs.getInt("class_id");
                products.add(new Student(id, name, dob,gender, new ClassModel(class_id, class_name)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }
    public Student findById(int id) {
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_STUDENT_BY_ID);) {
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
                Date dob = rs.getDate("dob");
                String gender = rs.getString("gender");
                int class_id = rs.getInt("class_id");
                String class_name = rs.getString("class_name");
                return new Student(idCus, name, dob,gender, new ClassModel(class_id, class_name));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void insertStudent(Student student) {
        System.out.println(INSERT_STUDENT);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)) {
            preparedStatement.setString(1, student.getName());
            java.sql.Date dateSQL = new java.sql.Date(student.getDob().getTime());
            preparedStatement.setDate(2,  dateSQL);
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setInt(4, student.getClassName().getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateStudent(Student student) {

        try (Connection connection = getConnection();
             //UPDATE `customers` " +
             //            "SET `name` = ?, `email` = ?, role_id = ? WHERE (`id` = ?);";
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {
            preparedStatement.setString(1, student.getName());
            java.sql.Date dateSQL = new java.sql.Date(student.getDob().getTime());
            preparedStatement.setDate(2,  dateSQL);
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setInt(4, student.getClassName().getId());
            preparedStatement.setInt(5, student.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteStudent(int id) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
