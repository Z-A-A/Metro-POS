package org.ZAA.backend.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ZAA.Controller.SuperAdmin;

public class SuperAdminController {

    private static final String DB_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12746761";
    private static final String DB_USER = "sql12746761";
    private static final String DB_PASSWORD = "Cr68DxeLq2";

    //create method to return list of superadmins from database
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