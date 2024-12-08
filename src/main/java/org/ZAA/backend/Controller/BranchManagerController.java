package org.ZAA.backend.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ZAA.Controller.BranchManager;

public class BranchManagerController {

    private static final String DB_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12746761";
    private static final String DB_USER = "sql12746761";
    private static final String DB_PASSWORD = "Cr68DxeLq2";

    // Method to add a branch manager to the database
    public boolean addBranchManager(BranchManager branchManager) {
        String query = "INSERT INTO BranchManagers (Name, EmployeeNo, Email, Password, BranchCode, Salary) VALUES (?, ?, ?, ?, ?, ?)";
        BranchController branchController = new BranchController();
        if(!branchController.checkBranchCode(branchManager.getBranchCode())){
            return false;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, branchManager.getName());
            preparedStatement.setInt(2, branchManager.getEmployeeNumber());
            preparedStatement.setString(3, branchManager.getEmail());
            preparedStatement.setString(4, branchManager.getPassword());
            preparedStatement.setString(5, branchManager.getBranchCode());
            preparedStatement.setDouble(6, branchManager.getSalary());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get a list of branch managers from the database
    public List<BranchManager> getAllBranchManagers() {
        List<BranchManager> branchManagers = new ArrayList<>();
        String query = "SELECT * FROM BranchManagers";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                BranchManager branchManager = new BranchManager(
                        resultSet.getString("Name"),
                        resultSet.getInt("EmployeeNo"),
                        resultSet.getString("Email"),
                        resultSet.getString("BranchCode"),
                        resultSet.getDouble("Salary")
                );
                branchManagers.add(branchManager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return branchManagers;
    }

    //method to change branch manager password by email
    public boolean changePassword(String email, String newPassword) {
        String query = "UPDATE BranchManagers SET Password = ? WHERE Email = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}