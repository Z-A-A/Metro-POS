package org.ZAA.backend.Controller;

import org.ZAA.Controller.SuperAdmin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuperAdminController {

    private static final String DB_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12746761";
    private static final String DB_USER = "sql12746761";
    private static final String DB_PASSWORD = "Cr68DxeLq2";

    public List<SuperAdmin> getAllSuperAdmins() {
        List<SuperAdmin> superAdmins = new ArrayList<>();
        String query = "SELECT * FROM SuperAdmin";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                SuperAdmin superAdmin = new SuperAdmin(
                        resultSet.getString("Name"),
                        resultSet.getString("Email"),
                        resultSet.getString("Password")
                );
                superAdmins.add(superAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return superAdmins;
    }

    public static boolean changePassword(String email, String newPassword) {
        String query = "UPDATE SuperAdmin SET Password = ? WHERE Email = ?";

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
    public static SuperAdmin getSuperAdmin(String email, String password) {
        String query = "SELECT * FROM SuperAdmin WHERE Email = ? AND Password = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new SuperAdmin(
                        resultSet.getString("Name"),
                        resultSet.getString("Email"),
                        resultSet.getString("Password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}