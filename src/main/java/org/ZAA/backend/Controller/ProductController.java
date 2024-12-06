package org.ZAA.backend.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ZAA.Controller.Product;

public class ProductController {

    private static final String DB_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12746761";
    private static final String DB_USER = "sql12746761";
    private static final String DB_PASSWORD = "Cr68DxeLq2";

    // Method to add a product to the database
    public boolean addProduct(Product product) {
        String query = "INSERT INTO Products (ProductName, Category, OriginalPrice, SalePrice, PricePerUnit, PricePerCarton, VendorID, CurrentStock, ProductImagePath, IsActive) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setDouble(3, product.getOriginalPrice());
            preparedStatement.setDouble(4, product.getSalePrice());
            preparedStatement.setDouble(5, product.getPricebyUnit());
            preparedStatement.setDouble(6, product.getPricebyCarton());
            preparedStatement.setInt(7, product.getVendorName());
            preparedStatement.setInt(8, product.getQuantity());
            preparedStatement.setString(9, product.getImagePath());
            preparedStatement.setBoolean(10, true);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get a list of products from the database
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Products";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    //function to update product stock
    public boolean updateProductStock(int productID, int newStock) {
        String query = "UPDATE Products SET CurrentStock = ? WHERE ProductID = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, newStock);
            preparedStatement.setInt(2, productID);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}