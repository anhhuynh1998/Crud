package com.example.crud_employee.dao;

import com.example.crud_employee.dto.Pageable;
import com.example.crud_employee.model.Department;
import com.example.crud_employee.model.Employee;
import com.example.crud_employee.model.Gender;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/crud_employee";
    private String jdbcUsername = "root";
    //                            password của mình
    private String jdbcPassword = "nhatanh123456";
    private final String SELECT_EMPLOYEE =
            "select cr.*,s.name as " +
                    "department_name from employee cr left join department s on cr.department_id = s.id WHERE\n" +
                    "    lower(employee.`name`) LIKE ? OR lower(employee.email) LIKE ? OR lower(employee.gender) LIKE ? OR lower(employee.salary) LIKE ?\n" +
                    "        OR lower(department.`name`) LIKE ? ORDER BY ? ?  LIMIT ? OFFSET ? ;";
    private final String SELECT_EMPLOYEE_BY_ID = "SELECT employee.*, department.`name` as department_name " +
            "FROM employee LEFT JOIN department " + "ON employee.department_id = " +
            "department.id where employee.id = ?;";
    private final String INSERT_EMPLOYE = "INSERT INTO `employee` (`name`, `dob`,`gender`,`salary`, `department_id`) " +
            "VALUES (?, ?, ?,?,?);";
    private final String TOTAL_EMPLOYEE = "SELECT \n" +
            "    COUNT(1) as total \n" +
            "FROM\n" +
            "    employee\n" +
            "        LEFT JOIN\n" +
            "    department ON employee.department_id = department.id\n" +
            "WHERE\n" +
            "    lower(employee.`name`) LIKE ? OR lower(employee.gender) LIKE ?\n" +
            "        OR lower(department.`name`) LIKE ? ;";
    private String SELECT_ALL_EMPLOYE = "SELECT \n" +
            "    employee.*, department.`name` as department_name \n" +
            "FROM\n" +
            "    employee\n" +
            "        LEFT JOIN\n" +
            "    department ON employee.department_id = department.id\n" +
            "WHERE\n" +
            "    lower(employee.`name`) LIKE '%s' OR lower(employee.gender) LIKE '%s' \n" +
            "        OR lower(department.`name`) LIKE '%s' order by %s %s LIMIT %d OFFSET %d  ;\n";

    private final String UPDATE_EMPLOYEE = "UPDATE `employee` " +
            "SET `name` = ?, `dob` = ?,`gender` = ?,`salary`= ?, department_id = ? WHERE (`id` = ?);";
    private final String DELETE_EMPLOYEE = "DELETE FROM `employee` WHERE (`id` = ?);";

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

    public List<Employee> findALL(Pageable pageable) {
        List<Employee> employees = new ArrayList<>();
        String search = pageable.getSearch();
        if (search == null) {
            search = "";
        }
        search = "%" + search + "%";
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(String
                             .format(SELECT_ALL_EMPLOYE, search, search, search,
                                     pageable.getNameField(), pageable.getSortBy(),
                                     pageable.getTotalItem(), (pageable.getPage() - 1) * pageable.getTotalItem()))) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Date dob = rs.getDate("dob");
                Gender gender = Gender.valueOf(rs.getString("gender"));
                double salary = rs.getDouble("salary");
                String department_name = rs.getString("department_name");
                int department_id = rs.getInt("department_id");
                employees.add(new Employee(id, name, dob, gender, salary, new Department(department_id, department_name)));
            }
            PreparedStatement statementTotalEmployee = connection.prepareStatement(TOTAL_EMPLOYEE);
            statementTotalEmployee.setString(1, search);
            statementTotalEmployee.setString(2, search);
            statementTotalEmployee.setString(3, search);
            ResultSet rsTotalEmployee = statementTotalEmployee.executeQuery();
            while (rsTotalEmployee.next()) {
                double totalEmployee = rsTotalEmployee.getDouble("total");
                double totalItems = Double.parseDouble(pageable.getTotalItem() + "");
                int totalPages = (int)
                        Math.ceil(totalEmployee / totalItems);
                pageable.setTotalPage(totalPages);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }

    public Employee findById(int id) {
        try (Connection connection = getConnection();

             // Step 2: truyền câu lênh mình muốn chạy nằm ở trong này (SELECT_USERS)
             PreparedStatement preparedStatement = connection
                     .prepareStatement(SELECT_EMPLOYEE_BY_ID);) {
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
                Gender gender = Gender.valueOf(rs.getString("gender"));
                double salary = rs.getDouble("salary");
                int department_id = rs.getInt("department_id");
                String department_name = rs.getString("department_name");
                return new Employee(idCus, name, dob, gender, salary, new Department(department_id, department_name));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void insertEmployee(Employee employee) {
        System.out.println(INSERT_EMPLOYE);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYE)) {
            preparedStatement.setString(1, employee.getName());
            java.sql.Date dateSQL = new java.sql.Date(employee.getDob().getTime());
            preparedStatement.setDate(2, dateSQL);
            preparedStatement.setString(3, employee.getGender().toString());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setInt(5, employee.getDepartment().getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateEmployee(Employee employee) {

        try (Connection connection = getConnection();
             //UPDATE `customers` " +
             //            "SET `name` = ?, `email` = ?, role_id = ? WHERE (`id` = ?);";
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE)) {
            preparedStatement.setString(1, employee.getName());
            java.sql.Date dateSQL = new java.sql.Date(employee.getDob().getTime());
            preparedStatement.setDate(2, dateSQL);
            preparedStatement.setString(3, employee.getGender().toString());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setInt(5, employee.getDepartment().getId());
            preparedStatement.setInt(6, employee.getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteEmployee(int id) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
