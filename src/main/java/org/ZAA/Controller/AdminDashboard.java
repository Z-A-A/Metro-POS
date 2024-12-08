package org.ZAA.Controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.ZAA.backend.Controller.AdminDashboardDb;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@Component
@RestController
@RequestMapping("/api/adminDash")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AdminDashboard {

    private double totalSalesThisWeek;
    private int totalVendors;
    private double totalSalesOverall;
    private int productsSoldThisWeek;
    private double averageProductSaleValue;
    private Map<Double, Vendor> salesGraphByVendors;
    private Map<Double, Product> salesPieChartByProducts;
    private int totalUniqueCustomers;

    public AdminDashboard() {
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

    @JsonManagedReference
    public Map<Double, Vendor> getSalesGraphByVendors() {
        return salesGraphByVendors;
    }

    public void setSalesGraphByVendors(Map<Double, Vendor> salesGraphByVendors) {
        this.salesGraphByVendors = salesGraphByVendors;
    }

    @JsonManagedReference
    public Map<Double, Product> getSalesPieChartByProducts() {
        return salesPieChartByProducts;
    }

    public void setSalesPieChartByProducts(Map<Double, Product> salesPieChartByProducts) {
        this.salesPieChartByProducts = salesPieChartByProducts;
    }

    public int getTotalUniqueCustomers() {
        return totalUniqueCustomers;
    }

    public void setTotalUniqueCustomers(int totalUniqueCustomers) {
        this.totalUniqueCustomers = totalUniqueCustomers;
    }

    @GetMapping("/adminDashboard")
    public AdminDashboard getAdminDashboard(@RequestParam String id) {
        AdminDashboard adminDashboard = new AdminDashboard();
        adminDashboard.setTotalSalesThisWeek(10000.0);
        adminDashboard.setTotalVendors(50);
        adminDashboard.setTotalSalesOverall(500000.0);
        adminDashboard.setProductsSoldThisWeek(200);
        adminDashboard.setAverageProductSaleValue(50.0);
        adminDashboard.setTotalUniqueCustomers(1000);

// Adding dummy data to salesGraphByVendors
        Vendor vendor1 = new Vendor(1, "Vendor1", "123 Vendor St", "123-456-7890", "Branch1");
        Vendor vendor2 = new Vendor(2, "Vendor2", "456 Vendor Ave", "987-654-3210", "Branch2");
        adminDashboard.getSalesGraphByVendors().put(1000.0, vendor1);
        adminDashboard.getSalesGraphByVendors().put(2000.0, vendor2);

// Adding dummy data to salesPieChartByProducts
        Product product1 = new Product(1, "Product1", 100.0, 90.0, 10.0, 50.0, "Category1", "Description1", "Branch1", 1, 5, null);
        Product product2 = new Product(2, "Product2", 200.0, 180.0, 20.0, 100.0, "Category2", "Description2", "Branch1", 2, 3, null);
        adminDashboard.getSalesPieChartByProducts().put(30.0, product1);
        adminDashboard.getSalesPieChartByProducts().put(70.0, product2);

        AdminDashboardDb adminDashboardDb = new AdminDashboardDb();
        adminDashboard = adminDashboardDb.getAdminDashboard();
        return adminDashboard;
    }
}