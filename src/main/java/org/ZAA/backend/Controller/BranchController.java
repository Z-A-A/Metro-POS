package org.ZAA.backend.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ZAA.Controller.Branch;

public class BranchController {

    private static final String DB_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12746761";
    private static final String DB_USER = "sql12746761";
    private static final String DB_PASSWORD = "Cr68DxeLq2";

    // Method to add a branch to the database
    public boolean addBranch(Branch branch) {
        String query = "INSERT INTO Branches (BranchCode, BranchName, City, Address, Phone, NumEmployees, IsActive) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, branch.getBranchCode());
            preparedStatement.setString(2, branch.getName());
            preparedStatement.setString(3, branch.getCity());
            preparedStatement.setString(4, branch.getAddress());
            preparedStatement.setString(5, branch.getPhone());
            preparedStatement.setInt(6, branch.getNumberOfEmployees());
            preparedStatement.setBoolean(7, branch.isActive());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get a list of branches from the database
    public List<Branch> getAllBranches() {
        List<Branch> branches = new ArrayList<>();
        String query = "SELECT * FROM Branches";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Branch branch = new Branch(
                        resultSet.getString("BranchCode"),
                        resultSet.getString("BranchName"),
                        resultSet.getString("City"),
                        resultSet.getBoolean("IsActive"),
                        resultSet.getString("Address"),
                        resultSet.getString("Phone")
                );
                branch.incrementEmployeeCount(resultSet.getInt("NumEmployees"));
                branches.add(branch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return branches;
    }
}