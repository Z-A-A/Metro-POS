package org.ZAA.Controller;

public class DataEntryOperator
{
    private String name;
    private int employeeNumber;
    private String email;
    private String password;
    private String branchCode;
    private double salary;

    public DataEntryOperator(String name, int employeeNumber, String email, String branchCode, double salary)
    {
        this.name = name;
        this.employeeNumber = employeeNumber;
        this.email = email;
        this.password = "Password_123";
        this.branchCode = branchCode;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public double getSalary() {
        return salary;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
