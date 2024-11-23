package org.ZAA.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BranchManager
{
    private String name;
    private int employeeNumber;
    private String email;
    private String password;
    private String branchCode;
    private double salary;
    private METRO_POS_MAIN_CONTROLLER_CODE mainController;

    public BranchManager(String name, int employeeNumber, String email, String branchCode, double salary)
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

    public void setMainController(METRO_POS_MAIN_CONTROLLER_CODE mainController) {
        this.mainController = mainController;
    }

    @PostMapping("/addCashier")
    public boolean addCashier(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam double salary) {
        Cashier newCashier = new Cashier(name, mainController.getCashiers().size() + 1, email, branchCode, salary);
        mainController.getCashiers().add(newCashier);
        for (BranchManagement branchManagement : mainController.getBranches()) {
            if (branchManagement.getBranch().getBranchCode().equals(branchCode)) {
                branchManagement.addCashier(newCashier);
                // Add to DB here
                System.out.println("CASHIER ADDED SUCCESSFULLY: " + newCashier.getName());
                return true;
            }
        }
        System.out.println("CASHIER ADDITION FAILED");
        return false;
    }

    @PostMapping("/addDataEntryOperator")
    public boolean addDataEntryOperator(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam double salary) {
        DataEntryOperator newDataEntryOperator = new DataEntryOperator(name, mainController.getDataEntryOperators().size() + 1, email, branchCode, salary);
        mainController.getDataEntryOperators().add(newDataEntryOperator);
        for (BranchManagement branchManagement : mainController.getBranches()) {
            if (branchManagement.getBranch().getBranchCode().equals(branchCode)) {
                branchManagement.addDataEntryOperator(newDataEntryOperator);
                // Add to DB here
                System.out.println("DATA ENTRY OPERATOR ADDED SUCCESSFULLY: " + newDataEntryOperator.getName());
                return true;
            }
        }
        System.out.println("DATA ENTRY OPERATOR ADDITION FAILED");
        return false;
    }
}