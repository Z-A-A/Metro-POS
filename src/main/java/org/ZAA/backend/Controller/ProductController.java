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
    public static boolean addProduct(Product product) {
        String query = "INSERT INTO Products (ProductName, Category, OriginalPrice, SalePrice, PricePerUnit, PricePerCarton, VendorID, CurrentStock, ProductImagePath, IsActive, BranchCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setDouble(3, product.getOriginalPrice());
            preparedStatement.setDouble(4, product.getSalePrice());
            preparedStatement.setDouble(5, product.getPricebyUnit());
            preparedStatement.setDouble(6, product.getPricebyCarton());
            preparedStatement.setInt(7, product.getVendorId());
            preparedStatement.setInt(8, product.getQuantity());
            preparedStatement.setString(9, product.getImagePath());
            preparedStatement.setBoolean(10, true);
            preparedStatement.setString(11, product.getBranchCode());

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
                Product product = new Product();
                product.setId(resultSet.getInt("ProductID"));
                product.setName(resultSet.getString("ProductName"));
                product.setCategory(resultSet.getString("Category"));
                product.setOriginalPrice(resultSet.getDouble("OriginalPrice"));
                product.setSalePrice(resultSet.getDouble("SalePrice"));
                product.setPricebyUnit(resultSet.getDouble("PricePerUnit"));
                product.setPricebyCarton(resultSet.getDouble("PricePerCarton"));
                product.setVendorId(resultSet.getInt("VendorID"));
                product.setQuantity(resultSet.getInt("CurrentStock"));
                product.setImagePath(resultSet.getString("ProductImagePath"));
                product.setBranchCode(resultSet.getString("BranchCode"));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    //function to update product stock
    public static boolean updateProductStock(int productID, int newStock) {
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

    public static List<Product> getProductByBranchCode(String branchCode) {
        String query = "SELECT * FROM Products WHERE BranchCode = ?";
        List<Product> products = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, branchCode);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("ProductID"));
                product.setName(resultSet.getString("ProductName"));
                product.setCategory(resultSet.getString("Category"));
                product.setOriginalPrice(resultSet.getDouble("OriginalPrice"));
                product.setSalePrice(resultSet.getDouble("SalePrice"));
                product.setPricebyUnit(resultSet.getDouble("PricePerUnit"));
                product.setPricebyCarton(resultSet.getDouble("PricePerCarton"));
                product.setVendorId(resultSet.getInt("VendorID"));
                product.setQuantity(resultSet.getInt("CurrentStock"));
                product.setImagePath(resultSet.getString("ProductImagePath"));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    //create function to get product for certain vendor id and that vendor shoudl have specific branch code
    public static List<Product> getProductByVendorId(int vendorId, String branchCode) {
        String query = "SELECT * FROM Products WHERE VendorID = ? AND BranchCode = ?";
        List<Product> products = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, vendorId);
            preparedStatement.setString(2, branchCode);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("ProductID"));
                product.setName(resultSet.getString("ProductName"));
                product.setCategory(resultSet.getString("Category"));
                product.setOriginalPrice(resultSet.getDouble("OriginalPrice"));
                product.setSalePrice(resultSet.getDouble("SalePrice"));
                product.setPricebyUnit(resultSet.getDouble("PricePerUnit"));
                product.setPricebyCarton(resultSet.getDouble("PricePerCarton"));
                product.setVendorId(resultSet.getInt("VendorID"));
                product.setQuantity(resultSet.getInt("CurrentStock"));
                product.setImagePath(resultSet.getString("ProductImagePath"));
                product.setBranchCode(resultSet.getString("BranchCode"));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static Product getProductById(int productId) {
        String query = "SELECT * FROM Products WHERE ProductID = ?";
        Product product = new Product();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                product.setId(resultSet.getInt("ProductID"));
                product.setName(resultSet.getString("ProductName"));
                product.setCategory(resultSet.getString("Category"));
                product.setOriginalPrice(resultSet.getDouble("OriginalPrice"));
                product.setSalePrice(resultSet.getDouble("SalePrice"));
                product.setPricebyUnit(resultSet.getDouble("PricePerUnit"));
                product.setPricebyCarton(resultSet.getDouble("PricePerCarton"));
                product.setVendorId(resultSet.getInt("VendorID"));
                product.setQuantity(resultSet.getInt("CurrentStock"));
                product.setImagePath(resultSet.getString("ProductImagePath"));
                product.setBranchCode(resultSet.getString("BranchCode"));

                return product;
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }

    }
}