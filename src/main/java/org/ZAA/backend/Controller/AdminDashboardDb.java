package org.ZAA.backend.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.ZAA.Controller.AdminDashboard;
import org.ZAA.Controller.Vendor;
import org.ZAA.Controller.Product;

public class AdminDashboardDb {

    private static final String DB_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12746761";
    private static final String DB_USER = "sql12746761";
    private static final String DB_PASSWORD = "Cr68DxeLq2";

    public AdminDashboard getAdminDashboard() {
        AdminDashboard adminDashboard = new AdminDashboard();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            adminDashboard.setTotalSalesThisWeek(getTotalSalesThisWeek(connection));
            adminDashboard.setTotalVendors(getTotalVendors(connection));
            adminDashboard.setTotalSalesOverall(getTotalSalesOverall(connection));
            adminDashboard.setProductsSoldThisWeek(getProductsSoldThisWeek(connection));
            adminDashboard.setAverageProductSaleValue(getAverageProductSaleValue(connection));
            adminDashboard.setSalesGraphByVendors(getSalesGraphByVendors(connection));
            adminDashboard.setSalesPieChartByProducts(getSalesPieChartByProducts(connection));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adminDashboard;
    }

    private double getTotalSalesThisWeek(Connection connection) throws SQLException {
        String query = "SELECT SUM(TotalAmount) FROM Sales WHERE SaleDate >= DATE_SUB(CURDATE(), INTERVAL 1 WEEK)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        }
        return 0;
    }

    private int getTotalVendors(Connection connection) throws SQLException {
        String query = "SELECT COUNT(*) FROM Vendors";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

    private double getTotalSalesOverall(Connection connection) throws SQLException {
        String query = "SELECT SUM(TotalAmount) FROM Sales";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        }
        return 0;
    }

    private int getProductsSoldThisWeek(Connection connection) throws SQLException {
        String query = "SELECT SUM(Quantity) FROM Sales WHERE SaleDate >= DATE_SUB(CURDATE(), INTERVAL 1 WEEK)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

    private double getAverageProductSaleValue(Connection connection) throws SQLException {
        String query = "SELECT AVG(UnitPrice) FROM Sales";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        }
        return 0;
    }

    private Map<Double, Vendor> getSalesGraphByVendors(Connection connection) throws SQLException {
        Map<Double, Vendor> salesGraphByVendors = new HashMap<>();
        String query = "SELECT v.VendorID, v.VendorName, SUM(s.TotalAmount) AS TotalSales " +
                "FROM Vendors v JOIN Products p ON v.VendorID = p.VendorID " +
                "JOIN Sales s ON p.ProductID = s.ProductID " +
                "GROUP BY v.VendorID, v.VendorName";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int vendorID = resultSet.getInt("VendorID");
                String vendorName = resultSet.getString("VendorName");
                double totalSales = resultSet.getDouble("TotalSales");
                Vendor vendor = VendorControllerDb.getVendorById(vendorID);
                salesGraphByVendors.put(totalSales, vendor);
            }
        }
        return salesGraphByVendors;
    }

    private Map<Double, Product> getSalesPieChartByProducts(Connection connection) throws SQLException {
        Map<Double, Product> salesPieChartByProducts = new HashMap<>();
        String query = "SELECT p.ProductID, p.ProductName, SUM(s.TotalAmount) AS TotalSales " +
                "FROM Products p JOIN Sales s ON p.ProductID = s.ProductID " +
                "GROUP BY p.ProductID, p.ProductName";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int productID = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                double totalSales = resultSet.getDouble("TotalSales");
                Product product = ProductController.getProductById(productID);
                salesPieChartByProducts.put(totalSales, product);
            }
        }
        return salesPieChartByProducts;
    }


}
