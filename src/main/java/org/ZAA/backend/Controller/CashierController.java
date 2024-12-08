package org.ZAA.backend.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ZAA.Controller.Cashier;

public class CashierController {

    private static final String DB_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12746761";
    private static final String DB_USER = "sql12746761";
    private static final String DB_PASSWORD = "Cr68DxeLq2";

    // Method to add a cashier to the database
    public static boolean addCashier(Cashier cashier) {
        String query = "INSERT INTO Cashier (Name, EmployeeNo, Email, Password, BranchCode, Salary) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, cashier.getName());
            preparedStatement.setInt(2, cashier.getEmployeeNumber());
            preparedStatement.setString(3, cashier.getEmail());
            preparedStatement.setString(4, cashier.getPassword());
            preparedStatement.setString(5, cashier.getBranchCode());
            preparedStatement.setDouble(6, cashier.getSalary());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get a list of cashiers from the database
    public List<Cashier> getAllCashiers() {
        List<Cashier> cashiers = new ArrayList<>();
        String query = "SELECT * FROM Cashier";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Cashier cashier = new Cashier(
                        resultSet.getString("Name"),
                        resultSet.getInt("EmployeeNo"),
                        resultSet.getString("Email"),
                        resultSet.getString("BranchCode"),
                        resultSet.getDouble("Salary")
                );
                cashiers.add(cashier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cashiers;
    }

    public boolean changePassword(String email, String newPassword) {
        String query = "UPDATE Cashier SET Password = ? WHERE Email = ?";

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
    public static Cashier getCashier(String email, String password) {
        String query = "SELECT * FROM Cashier WHERE Email = ? AND Password = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Cashier(
                        resultSet.getString("Name"),
                        resultSet.getInt("EmployeeNo"),
                        resultSet.getString("Email"),
                        resultSet.getString("BranchCode"),
                        resultSet.getDouble("Salary")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}