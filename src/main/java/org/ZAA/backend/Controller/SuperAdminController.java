package org.ZAA.backend.Controller;

import org.ZAA.Controller.SuperAdmin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SuperAdminController {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/your_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public SuperAdmin getSuperAdminById(int superAdminId) {
        SuperAdmin superAdmin = null;
        String query = "SELECT * FROM SuperAdmin WHERE SuperAdminID = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, superAdminId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                superAdmin = new SuperAdmin(resultSet.getString("Name"), resultSet.getString("Email"), resultSet.getString("Password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return superAdmin;
    }
}