package org.ZAA.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Component
@RestController
@RequestMapping("/api/bills")
public class Bill
{
    private List<Product> products;
    private String billNumber;
    private String cashierName;
    private String branchCode;
    private String date;
    private double totalAmount;


    public Bill()
    {

    }

    public Bill(String billNumber, String branchCode, String cashierName, String date, List<Product> products, double totalAmount) {
        this.billNumber = billNumber;
        this.branchCode = branchCode;
        this.cashierName = cashierName;
        this.date = date;
        this.products = products;
        this.totalAmount = totalAmount;
    }


    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @PostMapping("/generateBill")
    public Bill generateBill(@RequestBody List<Product> products, @RequestParam String branchCode) {
        Bill newBill = new Bill();
        newBill.setProducts(products);
        newBill.setBranchCode(branchCode);
        newBill.setBillNumber(generateBillNumber());
        newBill.setDate(getCurrentDate());
        newBill.setTotalAmount(calculateTotalAmount(products));

        // Add the bill to the database
        //remove quantitites from the products table in database

        return newBill;
    }

    private String generateBillNumber()
    {
        return "BILL" + System.currentTimeMillis();
    }

    private String getCurrentDate()
    {
        return java.time.LocalDate.now().toString();
    }

    private double calculateTotalAmount(List<Product> products) {

        return products.stream().mapToDouble(Product::getSalePrice).sum();
    }


}