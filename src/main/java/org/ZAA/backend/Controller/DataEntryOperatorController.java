package org.ZAA.backend.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ZAA.Controller.DataEntryOperator;

public class DataEntryOperatorController {

    private static final String DB_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12746761";
    private static final String DB_USER = "sql12746761";
    private static final String DB_PASSWORD = "Cr68DxeLq2";

    // Method to add a data entry operator to the database
    public boolean addDataEntryOperator(DataEntryOperator dataEntryOperator) {
        String query = "INSERT INTO DataEntryOperator (Name, EmployeeNo, Email, Password, BranchCode, Salary) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, dataEntryOperator.getName());
            preparedStatement.setInt(2, dataEntryOperator.getEmployeeNumber());
            preparedStatement.setString(3, dataEntryOperator.getEmail());
            preparedStatement.setString(4, dataEntryOperator.getPassword());
            preparedStatement.setString(5, dataEntryOperator.getBranchCode());
            preparedStatement.setDouble(6, dataEntryOperator.getSalary());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get a list of data entry operators from the database
    public List<DataEntryOperator> getAllDataEntryOperators() {
        List<DataEntryOperator> dataEntryOperators = new ArrayList<>();
        String query = "SELECT * FROM DataEntryOperator";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                DataEntryOperator dataEntryOperator = new DataEntryOperator(
                        resultSet.getString("Name"),
                        resultSet.getInt("EmployeeNo"),
                        resultSet.getString("Email"),
                        resultSet.getString("BranchCode"),
                        resultSet.getDouble("Salary")
                );
                dataEntryOperators.add(dataEntryOperator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataEntryOperators;
    }
}