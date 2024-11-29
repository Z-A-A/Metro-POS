package org.ZAA.backend.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.ZAA.Controller.SuperAdmin;

public class SuperAdminController {

    private static final String DB_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12746761";
    private static final String DB_USER = "sql12746761";
    private static final String DB_PASSWORD = "Cr68DxeLq2";

    public SuperAdmin getSuperAdminById(int superAdminId) {
        SuperAdmin superAdmin = null;
        String query = "SELECT * FROM SuperAdmin WHERE SuperAdminID = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, superAdminId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                superAdmin = new SuperAdmin(resultSet.getString("Name"), resultSet.getString("Email"), resultSet.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return superAdmin;
    }

    public static void main(String[] args) {
        SuperAdminController superAdminController = new SuperAdminController();
        SuperAdmin superAdmin = superAdminController.getSuperAdminById(1);
        System.out.println(superAdmin.getName());
        System.out.println(superAdmin.getEmail());
        System.out.println(superAdmin.getPassword());
    }
}