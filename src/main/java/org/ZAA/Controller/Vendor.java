package org.ZAA.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Component
public class Vendor
{
    private int vendorID;
    private String vendorName;
    private String vendorAddress;
    private String vendorPhone;
    private String branchCode;

    @JsonIgnore
    private AdminDashboard adminDashboard;


    public Vendor()
    {

    }

    public Vendor(int vendorID, String vendorName, String vendorAddress, String vendorPhone, String branchCode)
    {
        this.vendorID = vendorID;
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.vendorPhone = vendorPhone;
        this.branchCode = branchCode;
    }

    public int getVendorID() {
        return vendorID;
    }

    public void setVendorID(int vendorID) {
        this.vendorID = vendorID;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getVendorPhone() {
        return vendorPhone;
    }

    public void setVendorPhone(String vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
}