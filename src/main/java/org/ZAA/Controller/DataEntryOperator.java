package org.ZAA.Controller;

public class DataEntryOperator
{
    private String name;
    private Integer employeeNumber;
    private String email;
    private String password;
    private String branchCode;
    private Double salary;

    public DataEntryOperator()
    {

    }

    public DataEntryOperator(String name, Integer employeeNumber, String email, String branchCode, Double salary)
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

    public Integer getEmployeeNumber() {
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

    public Double getSalary() {
        return salary;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}