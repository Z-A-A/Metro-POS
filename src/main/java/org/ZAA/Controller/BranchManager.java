package org.ZAA.Controller;

import org.ZAA.backend.Controller.CashierController;
import org.ZAA.backend.Controller.DataEntryOperatorController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BranchManager
{
    private String name;
    private Integer employeeNumber;
    private String email;
    private String password;
    private String branchCode;
    private Double salary;
    private METRO_POS_MAIN_CONTROLLER_CODE mainController;


    public BranchManager() {
        // Default constructor for Spring
    }

    public BranchManager(String name, Integer employeeNumber, String email, String branchCode, Double salary)
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

    public void setMainController(METRO_POS_MAIN_CONTROLLER_CODE mainController) {
        this.mainController = mainController;
    }

    @PostMapping("/addCashier")
    public boolean addCashier(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam Double salary) {
        Cashier newCashier = new Cashier(name, mainController.getCashiers().size() + 1, email, branchCode, salary);
        mainController.getCashiers().add(newCashier);
        for (BranchManagement branchManagement : mainController.getBranches()) {
            if (branchManagement.getBranch().getBranchCode().equals(branchCode)) {
                branchManagement.addCashier(newCashier);
                // Add to DB here
                if(CashierController.addCashier(newCashier))
                {
                    System.out.println("CASHIER ADDED SUCCESSFULLY: " + newCashier.getName());
                    return true;
                }
                else {
                    System.out.println("CASHIER ADDITION FAILED");
                    return false;
                }
            }
        }
        System.out.println("CASHIER ADDITION FAILED");
        return false;
    }

    @PostMapping("/addDataEntryOperator")
    public boolean addDataEntryOperator(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam Double salary) {
        DataEntryOperator newDataEntryOperator = new DataEntryOperator(name, mainController.getDataEntryOperators().size() + 1, email, branchCode, salary);
        mainController.getDataEntryOperators().add(newDataEntryOperator);
        for (BranchManagement branchManagement : mainController.getBranches()) {
            if (branchManagement.getBranch().getBranchCode().equals(branchCode)) {
                branchManagement.addDataEntryOperator(newDataEntryOperator);
                // Add to DB here
                if(DataEntryOperatorController.addDataEntryOperator(newDataEntryOperator))
                {
                    System.out.println("DATA ENTRY OPERATOR ADDED SUCCESSFULLY: " + newDataEntryOperator.getName());
                    return true;
                }
                else {
                    System.out.println("DATA ENTRY OPERATOR ADDITION FAILED");
                    return false;
                }
            }
        }
        System.out.println("DATA ENTRY OPERATOR ADDITION FAILED");
        return false;
    }
}