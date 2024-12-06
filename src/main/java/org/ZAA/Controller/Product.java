package org.ZAA.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Product
{
    private int id;
    private String name;
    private double originalPrice;
    private double salePrice;
    private double pricebyUnit;
    private double pricebyCarton;
    private String category;
    private String description;
    private String branchCode;
    private String vendorName;
    private int quantity;
    private String imagePath;

    public Product(int id, String name, double originalPrice, double salePrice, double priceByUnit, double priceByCarton, String category, String description, String branchCode, String vendorCode, int quantity, String imagePath) {
        this.id = id;
        this.name = name;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.pricebyUnit = priceByUnit;
        this.pricebyCarton = priceByCarton;
        this.category = category;
        this.description = description;
        this.branchCode = branchCode;
        this.vendorName = vendorCode;
        this.quantity = quantity;
        this.imagePath = imagePath; // Initialize the new field
    }


    public Product()
    {

    }


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

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }



}