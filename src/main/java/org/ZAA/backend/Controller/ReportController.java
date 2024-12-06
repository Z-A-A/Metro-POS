package org.ZAA.backend.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.ZAA.Controller.Report;

public class ReportController {

    private static final String DB_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12746761";
    private static final String DB_USER = "sql12746761";
    private static final String DB_PASSWORD = "Cr68DxeLq2";

    public Report getBranchReport(String branchCode) {
        double totalSales = 0;
        int remainingStock = 0;
        double totalProfit = 0;

        String billsQuery = "SELECT * FROM Bills WHERE BranchCode = ?";
        String productsQuery = "SELECT * FROM Products WHERE BranchCode = ?";
        String salesQuery = "SELECT * FROM Sales WHERE BranchCode = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement billsStmt = connection.prepareStatement(billsQuery);
             PreparedStatement productsStmt = connection.prepareStatement(productsQuery);
             PreparedStatement salesStmt = connection.prepareStatement(salesQuery)) {

            // Get total sales amount
            billsStmt.setString(1, branchCode);
            ResultSet billsResultSet = billsStmt.executeQuery();
            while (billsResultSet.next()) {
                totalSales += billsResultSet.getDouble("TotalAmount");
            }

            // Get remaining stock
            productsStmt.setString(1, branchCode);
            ResultSet productsResultSet = productsStmt.executeQuery();
            while (productsResultSet.next()) {
                remainingStock += productsResultSet.getInt("CurrentStock");
            }

            // Calculate profit
            salesStmt.setString(1, branchCode);
            ResultSet salesResultSet = salesStmt.executeQuery();
            while (salesResultSet.next()) {
                int productId = salesResultSet.getInt("ProductID");
                int quantitySold = salesResultSet.getInt("Quantity");
                double unitPrice = salesResultSet.getDouble("UnitPrice");

                // Get original price of the product
                String productPriceQuery = "SELECT OriginalPrice FROM Products WHERE ProductID = ?";
                try (PreparedStatement productPriceStmt = connection.prepareStatement(productPriceQuery)) {
                    productPriceStmt.setInt(1, productId);
                    ResultSet productPriceResultSet = productPriceStmt.executeQuery();
                    if (productPriceResultSet.next()) {
                        double originalPrice = productPriceResultSet.getDouble("OriginalPrice");
                        totalProfit += (unitPrice - originalPrice) * quantitySold;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Report(totalSales, remainingStock, totalProfit);
    }

    public Report getDailyReport(String branchCode) {
        return getReport(branchCode, "DAY");
    }

    public Report getMonthlyReport(String branchCode) {
        return getReport(branchCode, "MONTH");
    }

    public Report getYearlyReport(String branchCode) {
        return getReport(branchCode, "YEAR");
    }

    public Report getRangeReport(String branchCode, String startDate, String endDate) {
        return getReport(branchCode, startDate, endDate);
    }

    private Report getReport(String branchCode, String period) {
        double totalSales = 0;
        int remainingStock = 0;
        double totalProfit = 0;

        String billsQuery = "SELECT * FROM Bills WHERE BranchCode = ? AND CreationDate >= DATE_SUB(CURDATE(), INTERVAL 1 " + period + ")";
        String productsQuery = "SELECT * FROM Products WHERE BranchCode = ?";
        String salesQuery = "SELECT * FROM Sales WHERE BranchCode = ? AND SaleDate >= DATE_SUB(CURDATE(), INTERVAL 1 " + period + ")";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement billsStmt = connection.prepareStatement(billsQuery);
             PreparedStatement productsStmt = connection.prepareStatement(productsQuery);
             PreparedStatement salesStmt = connection.prepareStatement(salesQuery)) {

            // Get total sales amount
            billsStmt.setString(1, branchCode);
            ResultSet billsResultSet = billsStmt.executeQuery();
            while (billsResultSet.next()) {
                totalSales += billsResultSet.getDouble("TotalAmount");
            }

            // Get remaining stock
            productsStmt.setString(1, branchCode);
            ResultSet productsResultSet = productsStmt.executeQuery();
            while (productsResultSet.next()) {
                remainingStock += productsResultSet.getInt("CurrentStock");
            }

            // Calculate profit
            salesStmt.setString(1, branchCode);
            ResultSet salesResultSet = salesStmt.executeQuery();
            while (salesResultSet.next()) {
                int productId = salesResultSet.getInt("ProductID");
                int quantitySold = salesResultSet.getInt("Quantity");
                double unitPrice = salesResultSet.getDouble("UnitPrice");

                // Get original price of the product
                String productPriceQuery = "SELECT OriginalPrice FROM Products WHERE ProductID = ?";
                try (PreparedStatement productPriceStmt = connection.prepareStatement(productPriceQuery)) {
                    productPriceStmt.setInt(1, productId);
                    ResultSet productPriceResultSet = productPriceStmt.executeQuery();
                    if (productPriceResultSet.next()) {
                        double originalPrice = productPriceResultSet.getDouble("OriginalPrice");
                        totalProfit += (unitPrice - originalPrice) * quantitySold;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Report(totalSales, remainingStock, totalProfit);
    }

    private Report getReport(String branchCode, String startDate, String endDate) {
        double totalSales = 0;
        int remainingStock = 0;
        double totalProfit = 0;

        String billsQuery = "SELECT * FROM Bills WHERE BranchCode = ? AND CreationDate BETWEEN ? AND ?";
        String productsQuery = "SELECT * FROM Products WHERE BranchCode = ?";
        String salesQuery = "SELECT * FROM Sales WHERE BranchCode = ? AND SaleDate BETWEEN ? AND ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement billsStmt = connection.prepareStatement(billsQuery);
             PreparedStatement productsStmt = connection.prepareStatement(productsQuery);
             PreparedStatement salesStmt = connection.prepareStatement(salesQuery)) {

            // Get total sales amount
            billsStmt.setString(1, branchCode);
            billsStmt.setString(2, startDate);
            billsStmt.setString(3, endDate);
            ResultSet billsResultSet = billsStmt.executeQuery();
            while (billsResultSet.next()) {
                totalSales += billsResultSet.getDouble("TotalAmount");
            }

            // Get remaining stock
            productsStmt.setString(1, branchCode);
            ResultSet productsResultSet = productsStmt.executeQuery();
            while (productsResultSet.next()) {
                remainingStock += productsResultSet.getInt("CurrentStock");
            }

            // Calculate profit
            salesStmt.setString(1, branchCode);
            salesStmt.setString(2, startDate);
            salesStmt.setString(3, endDate);
            ResultSet salesResultSet = salesStmt.executeQuery();
            while (salesResultSet.next()) {
                int productId = salesResultSet.getInt("ProductID");
                int quantitySold = salesResultSet.getInt("Quantity");
                double unitPrice = salesResultSet.getDouble("UnitPrice");

                // Get original price of the product
                String productPriceQuery = "SELECT OriginalPrice FROM Products WHERE ProductID = ?";
                try (PreparedStatement productPriceStmt = connection.prepareStatement(productPriceQuery)) {
                    productPriceStmt.setInt(1, productId);
                    ResultSet productPriceResultSet = productPriceStmt.executeQuery();
                    if (productPriceResultSet.next()) {
                        double originalPrice = productPriceResultSet.getDouble("OriginalPrice");
                        totalProfit += (unitPrice - originalPrice) * quantitySold;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Report(totalSales, remainingStock, totalProfit);
    }

    public Report getWeeklyReport(String branchCode) {
        return getReport(branchCode, "WEEK");
    }
}