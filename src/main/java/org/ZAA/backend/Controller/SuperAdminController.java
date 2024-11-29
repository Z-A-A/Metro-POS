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

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/your_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

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
}