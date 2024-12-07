package org.ZAA.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.ZAA.backend.Controller.VendorControllerDb;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;
import java.util.List;

@Component
@RestController
@RequestMapping("/api/adminDashboard")
public class AdminDashboard
{

    private double totalSalesThisWeek;
    private int totalVendors;
    private double totalSalesOverall;
    private int productsSoldThisWeek;
    private double averageProductSaleValue;
    private Map<Integer, Vendor> salesGraphByVendors;
    private Map<Integer, Product> salesPieChartByProducts;
    private int totalUniqueCustomers;

    public AdminDashboard()
    {
        // Initialize the maps with a custom comparator for descending order
        salesGraphByVendors = new TreeMap<>(Collections.reverseOrder());
        salesPieChartByProducts = new TreeMap<>(Collections.reverseOrder());
    }

    // Getters and setters for each field

    public double getTotalSalesThisWeek() {
        return totalSalesThisWeek;
    }

    public void setTotalSalesThisWeek(double totalSalesThisWeek) {
        this.totalSalesThisWeek = totalSalesThisWeek;
    }

    public int getTotalVendors() {
        return totalVendors;
    }

    public void setTotalVendors(int totalVendors) {
        this.totalVendors = totalVendors;
    }

    public double getTotalSalesOverall() {
        return totalSalesOverall;
    }

    public void setTotalSalesOverall(double totalSalesOverall) {
        this.totalSalesOverall = totalSalesOverall;
    }

    public int getProductsSoldThisWeek() {
        return productsSoldThisWeek;
    }

    public void setProductsSoldThisWeek(int productsSoldThisWeek) {
        this.productsSoldThisWeek = productsSoldThisWeek;
    }

    public double getAverageProductSaleValue() {
        return averageProductSaleValue;
    }

    public void setAverageProductSaleValue(double averageProductSaleValue) {
        this.averageProductSaleValue = averageProductSaleValue;
    }

    public Map<Integer, Vendor> getSalesGraphByVendors() {
        return salesGraphByVendors;
    }

    public void setSalesGraphByVendors(Map<Integer, Vendor> salesGraphByVendors) {
        this.salesGraphByVendors = salesGraphByVendors;
    }

    public Map<Integer, Product> getSalesPieChartByProducts() {
        return salesPieChartByProducts;
    }

    public void setSalesPieChartByProducts(Map<Integer, Product> salesPieChartByProducts) {
        this.salesPieChartByProducts = salesPieChartByProducts;
    }

    public int getTotalUniqueCustomers() {
        return totalUniqueCustomers;
    }

    public void setTotalUniqueCustomers(int totalUniqueCustomers) {
        this.totalUniqueCustomers = totalUniqueCustomers;
    }

    @GetMapping("/adminDashboard")
    public AdminDashboard getAdminDashboard()
    {
        AdminDashboard adminDashboard = new AdminDashboard();
        //adminDashboard = AdminControllerDb.getAdminDashboard();
        //DB INTERACTION HERE

        return new AdminDashboard();
    }
}