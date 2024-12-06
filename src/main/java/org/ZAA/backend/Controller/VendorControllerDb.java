package org.ZAA.backend.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ZAA.Controller.Vendor;

public class VendorControllerDb {

    private static final String DB_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12746761";
    private static final String DB_USER = "sql12746761";
    private static final String DB_PASSWORD = "Cr68DxeLq2";

    // Method to add a new vendor to the database
    public static boolean addVendor(Vendor vendor) {
        String query = "INSERT INTO Vendors (VendorID, VendorName, VendorAddress, VendorPhone, BranchCode) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, vendor.getVendorID());
            preparedStatement.setString(2, vendor.getVendorName());
            preparedStatement.setString(3, vendor.getVendorAddress());
            preparedStatement.setString(4, vendor.getVendorPhone());
            preparedStatement.setString(5, vendor.getBranchCode());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get all vendors from the database
    public List<Vendor> getAllVendors() {
        List<Vendor> vendors = new ArrayList<>();
        String query = "SELECT * FROM Vendors";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Vendor vendor = new Vendor(
                        resultSet.getInt("VendorID"),
                        resultSet.getString("VendorName"),
                        resultSet.getString("VendorAddress"),
                        resultSet.getString("VendorPhone"),
                        resultSet.getString("BranchCode")
                );
                vendors.add(vendor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendors;
    }

    // Method to get a vendor by ID from the database
    public Vendor getVendorById(int vendorID) {
        String query = "SELECT * FROM Vendors WHERE VendorID = ?";
        Vendor vendor = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, vendorID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                vendor = new Vendor(
                        resultSet.getInt("VendorID"),
                        resultSet.getString("VendorName"),
                        resultSet.getString("VendorAddress"),
                        resultSet.getString("VendorPhone"),
                        resultSet.getString("BranchCode")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendor;
    }

    // Method to update a vendor in the database
    public boolean updateVendor(Vendor vendor) {
        String query = "UPDATE Vendors SET VendorName = ?, VendorAddress = ?, VendorPhone = ?, BranchCode = ? WHERE VendorID = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, vendor.getVendorName());
            preparedStatement.setString(2, vendor.getVendorAddress());
            preparedStatement.setString(3, vendor.getVendorPhone());
            preparedStatement.setString(4, vendor.getBranchCode());
            preparedStatement.setInt(5, vendor.getVendorID());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a vendor from the database
    public boolean deleteVendor(int vendorID) {
        String query = "DELETE FROM Vendors WHERE VendorID = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, vendorID);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}