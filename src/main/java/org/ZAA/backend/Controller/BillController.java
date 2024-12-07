package org.ZAA.backend.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ZAA.Controller.Bill;
import org.ZAA.Controller.Product;

public class BillController {

    private static final String DB_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12746761";
    private static final String DB_USER = "sql12746761";
    private static final String DB_PASSWORD = "Cr68DxeLq2";

    // Method to add a bill to the database
    public boolean addBill(Bill bill) {
        String query = "INSERT INTO Bills (BillNumber, BranchCode, CashierName, TotalAmount) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, bill.getBillNumber());
            preparedStatement.setString(2, bill.getBranchCode());
            preparedStatement.setString(3, bill.getCashierName());
            preparedStatement.setDouble(4, bill.getTotalAmount());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                addSalesEntries(bill);
                updateProductStock(bill);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to add sales entries for each product in the bill
    private void addSalesEntries(Bill bill) throws SQLException {
        String query = "INSERT INTO Sales (BillNumber, BranchCode, ProductID, Quantity, UnitPrice, TotalAmount, CashierUserID) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (Product product : bill.getProducts()) {
                preparedStatement.setString(1, bill.getBillNumber());
                preparedStatement.setString(2, bill.getBranchCode());
                preparedStatement.setInt(3, product.getId());
                preparedStatement.setInt(4, product.getQuantity());
                preparedStatement.setDouble(5, product.getSalePrice());
                preparedStatement.setDouble(6, product.getSalePrice() * product.getQuantity());
                preparedStatement.setInt(7, getCashierUserID(bill.getCashierName()));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }
    private void updateProductStock(Bill bill) throws SQLException {
        String query = "UPDATE Products SET CurrentStock = CurrentStock - ? WHERE ProductID = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (Product product : bill.getProducts()) {
                preparedStatement.setInt(1, product.getQuantity());
                preparedStatement.setInt(2, product.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    // Method to get the cashier user ID from the cashier name
    private int getCashierUserID(String cashierName) throws SQLException {
        String query = "SELECT EmployeeNo FROM Cashier WHERE Name = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, cashierName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("EmployeeNo");
            } else {
                throw new SQLException("Cashier not found");
            }
        }
    }

    // Method to get a list of bills from the database
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM Bills";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Bill bill = new Bill(
                        resultSet.getString("BillNumber"),
                        resultSet.getString("BranchCode"),
                        resultSet.getString("CashierName"),
                        resultSet.getString("Date"),
                        getBillProducts(resultSet.getString("BillNumber")),
                        resultSet.getDouble("TotalAmount")
                );
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bills;
    }

    // Method to get products associated with a bill from the database
    private List<Product> getBillProducts(String billNumber) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.* FROM Products p JOIN BillProducts bp ON p.ProductID = bp.ProductID WHERE bp.BillNumber = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, billNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("ProductID"),
                        resultSet.getString("ProductName"),
                        resultSet.getDouble("OriginalPrice"),
                        resultSet.getDouble("SalePrice"),
                        resultSet.getDouble("PricePerUnit"),
                        resultSet.getDouble("PricePerCarton"),
                        resultSet.getString("Category"),
                        resultSet.getString("Description"),
                        resultSet.getString("BranchCode"),
                        resultSet.getInt("VendorID"),
                        resultSet.getInt("CurrentStock"),
                        resultSet.getString("ProductImagePath")
                );
                products.add(product);
            }
        }
        return products;
    }
}
