package org.ZAA.Controller;

public class Product {
    private int id;
    private String name;
    private double originalPrice;
    private double salePrice;
    private double pricebyUnit;
    private double pricebyCarton;
    private String category;
    private String description;
    private String branchCode;
    private int vendorId;
    private int quantity;
    private String imagePath; // Remove this field
    private byte[] image; // New field for image data
    public Product(int id, String name, double originalPrice, double salePrice, double priceByUnit, double priceByCarton, String category, String description, String branchCode, int vendorId, int quantity, byte[] image, String imagePath) {

        this.id = id;
        this.name = name;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.pricebyUnit = priceByUnit;
        this.pricebyCarton = priceByCarton;
        this.category = category;
        this.description = description;
        this.branchCode = branchCode;

        this.vendorId = vendorId;

        this.quantity = quantity;
        this.image = image; // Initialize the new field
        this.imagePath = imagePath; // Remove this line
    }

    public Product() {
    }

    public Product(int productID, String productName, double originalPrice, double salePrice, double pricePerUnit, double pricePerCarton, String category, String description, String branchCode, int vendorID, int currentStock, String productImagePath) {
    }

    public Product(int i, String product1, double v, double v1, double v2, double v3, String category1, String description1, String br001, String vendor1, int i1) {
    }

    // Getters and setters for all fields, including the new image field

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getPricebyCarton() {
        return pricebyCarton;
    }

    public void setPricebyCarton(double pricebyCarton) {
        this.pricebyCarton = pricebyCarton;
    }

    public double getPricebyUnit() {
        return pricebyUnit;
    }

    public void setPricebyUnit(double pricebyUnit) {
        this.pricebyUnit = pricebyUnit;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }
    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}