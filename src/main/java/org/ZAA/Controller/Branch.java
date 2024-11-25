package org.ZAA.Controller;

public class Branch
{
    private String branchCode;
    private String name;
    private String city;
    private Boolean isActive;
    private String address;
    private String phone;
    private Integer numberOfEmployees;

    public Branch(String branchCode, String name, String city, Boolean isActive, String address, String phone)
    {
        this.branchCode = branchCode;
        this.name = name;
        this.city = city;
        this.isActive = isActive;
        this.address = address;
        this.phone = phone;
        this.numberOfEmployees = 0;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Boolean isActive() {
        return isActive;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void incrementEmployeeCount() {
        numberOfEmployees++;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }
}