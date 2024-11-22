package org.ZAA.Controller;

import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class METRO_POS_MAIN_CONTROLLER_CODE
{
    private List<SuperAdmin> superAdmins;
    private List<BranchManagement> branches;

    private List<BranchManager> admins;
    private List<Cashier> cashiers;
    private List<DataEntryOperator> dataEntryOperators;

    public METRO_POS_MAIN_CONTROLLER_CODE()
    {
        this.superAdmins = new ArrayList<>();
        this.branches = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.cashiers = new ArrayList<>();
        this.dataEntryOperators = new ArrayList<>();
    }

    public void loadData()
    {
        // LOAD DATA FROM DATABASE INTO LISTS.
        TEST_LOAD_WITHOUT_DB();
    }

    public void syncDataToDataBase()
    {
        //STORE DATA COLLECTIONS INTO
    }

    //TESTING METHOD.
    public void TEST_LOAD_WITHOUT_DB()
    {
        SuperAdmin superAdmin1 = new SuperAdmin("Admin1", "admin1@example.com", "admin123");
        superAdmin1.setMainController(this);
        SuperAdmin superAdmin2 = new SuperAdmin("Admin2", "admin2@example.com", "admin123");
        superAdmin2.setMainController(this);
        superAdmins.add(superAdmin1);
        superAdmins.add(superAdmin2);
        System.out.println("SUPER ADMINS LOADED SUCCESSFULLY");

        Branch branch1 = new Branch("B001", "Main Branch", "City A", true, "123 Street", "123-456-7890");
        BranchManagement branchManagement1 = new BranchManagement(branch1);
        Branch branch2 = new Branch("B002", "Secondary Branch", "City B", true, "456 Avenue", "987-654-3210");
        BranchManagement branchManagement2 = new BranchManagement(branch2);
        branches.add(branchManagement1);
        branches.add(branchManagement2);
        System.out.println("BRANCHES LOADED SUCCESSFULLY");

        BranchManager branchManager1 = new BranchManager("Alice", 101, "alice@example.com", "B001", 50000);
        branchManager1.setMainController(this);
        branchManagement1.addBranchManager(branchManager1);
        admins.add(branchManager1);

        BranchManager branchManager2 = new BranchManager("Bob", 102, "bob@example.com", "B002", 55000);
        branchManager2.setMainController(this);
        branchManagement2.addBranchManager(branchManager2);
        admins.add(branchManager2);

        Cashier cashier1 = new Cashier("John", 201, "john@example.com", "B001", 30000);
        branchManagement1.addCashier(cashier1);
        cashiers.add(cashier1);

        Cashier cashier2 = new Cashier("Jane", 202, "jane@example.com", "B002", 32000);
        branchManagement2.addCashier(cashier2);
        cashiers.add(cashier2);

        DataEntryOperator dataEntryOperator1 = new DataEntryOperator("Charlie", 301, "charlie@example.com", "B001", 25000);
        branchManagement1.addDataEntryOperator(dataEntryOperator1);
        dataEntryOperators.add(dataEntryOperator1);

        DataEntryOperator dataEntryOperator2 = new DataEntryOperator("David", 302, "david@example.com", "B002", 27000);
        branchManagement2.addDataEntryOperator(dataEntryOperator2);
        dataEntryOperators.add(dataEntryOperator2);

        System.out.println("BRANCH DATA LOADED SUCCESSFULLY");
    }

    //LOGIN METHODS FOR GUI.

    @GetMapping("/login/superadmin")
    public SuperAdmin loginSuperAdmin(@RequestParam String email, @RequestParam String password) {
        for (SuperAdmin superAdmin : superAdmins) {
            if (superAdmin.getEmail().equals(email) && superAdmin.getPassword().equals(password)) {
                System.out.println("SUPER ADMIN LOGIN SUCCESSFUL");
                return superAdmin;
            }
        }
        System.out.println("SUPER ADMIN LOGIN FAILED");
        return null;
    }

    @GetMapping("/login/admin")
    public BranchManager loginAdmin(@RequestParam String email, @RequestParam String password) {
        for (BranchManager admin : admins) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                System.out.println("BRANCH MANAGER LOGIN SUCCESSFUL");
                return admin;
            }
        }
        System.out.println("BRANCH MANAGER LOGIN FAILED");
        return null;
    }

    @GetMapping("/login/cashier")
    public Cashier loginCashier(@RequestParam String email, @RequestParam String password) {
        for (Cashier cashier : cashiers) {
            if (cashier.getEmail().equals(email) && cashier.getPassword().equals(password)) {
                System.out.println("CASHIER LOGIN SUCCESSFUL");
                return cashier;
            }
        }
        System.out.println("CASHIER LOGIN FAILED");
        return null;
    }

    @GetMapping("/login/dataentryoperator")
    public DataEntryOperator loginDataEntryOperator(@RequestParam String email, @RequestParam String password) {
        for (DataEntryOperator dataEntryOperator : dataEntryOperators) {
            if (dataEntryOperator.getEmail().equals(email) && dataEntryOperator.getPassword().equals(password)) {
                System.out.println("DATA ENTRY OPERATOR LOGIN SUCCESSFUL");
                return dataEntryOperator;
            }
        }
        System.out.println("DATA ENTRY OPERATOR LOGIN FAILED");
        return null;
    }

    //SIGN UP METHODS FOR GUI.

    public void signUpSuperAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Super Admin Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Super Admin Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Super Admin Password: ");
        String password = scanner.nextLine();
        SuperAdmin newSuperAdmin = new SuperAdmin(name, email, password);
        superAdmins.add(newSuperAdmin);
        // ALSO ADD IN DATABASEEE HERE==============================
        System.out.println("SUPER ADMIN SIGN UP SUCCESSFUL: " + newSuperAdmin.getName());
    }

    @PostMapping("/signup/branchmanager")
    public boolean signUpBranchManager(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String branchCode, @RequestParam double salary) {
        BranchManager newBranchManager = new BranchManager(name, admins.size() + 1, email, branchCode, salary);
        admins.add(newBranchManager);
        // ALSO ADD IN DATABASEEE HERE ===============================
        System.out.println("ADMIN (BRANCH MANAGER) ADDED SUCCESSFULLY: " + newBranchManager.getName());
        return true;
    }

    @PostMapping("/signup/cashier")
    public boolean signUpCashier(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String branchCode, @RequestParam double salary) {
        Cashier newCashier = new Cashier(name, cashiers.size() + 1, email, branchCode, salary);
        cashiers.add(newCashier);
        // ALSO ADD IN DATABASEEEEE HERE ===============================
        System.out.println("CASHIER ADDED SUCCESSFULLY: " + newCashier.getName());
        return true;
    }

    @PostMapping("/signup/dataentryoperator")
    public boolean signUpDataEntryOperator(@RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String branchCode, @RequestParam double salary) {
        DataEntryOperator newDataEntryOperator = new DataEntryOperator(name, dataEntryOperators.size() + 1, email, branchCode, salary);
        dataEntryOperators.add(newDataEntryOperator);
        // ALSO ADD IN DATABASEEE HERE ===============================
        System.out.println("DATA ENTRY OPERATOR ADDED SUCCESSFULLY: " + newDataEntryOperator.getName());
        return true;
    }

    //PASSWORD CHANGE METHODS FOR GUI.

    @PostMapping("/changePassword/superadmin")
    public boolean changeSuperAdminPassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword) {
        for (SuperAdmin superAdmin : superAdmins) {
            if (superAdmin.getEmail().equals(email) && superAdmin.getPassword().equals(oldPassword)) {
                superAdmin.setPassword(newPassword);
                //CHANGE IN DB HERE==============================
                System.out.println("SUPER ADMIN PASSWORD CHANGED SUCCESSFULLY");
                return true;
            }
        }
        System.out.println("SUPER ADMIN PASSWORD CHANGE FAILED");
        return false;
    }

    @PostMapping("/changePassword/branchmanager")
    public boolean changeBranchManagerPassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword) {
        for (BranchManager admin : admins) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(oldPassword)) {
                admin.setPassword(newPassword);
                //CHANGE IN DB HERE==============================
                System.out.println("BRANCH MANAGER PASSWORD CHANGED SUCCESSFULLY");
                return true;
            }
        }
        System.out.println("BRANCH MANAGER PASSWORD CHANGE FAILED");
        return false;
    }

    @PostMapping("/changePassword/cashier")
    public boolean changeCashierPassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword) {
        for (Cashier cashier : cashiers) {
            if (cashier.getEmail().equals(email) && cashier.getPassword().equals(oldPassword)) {
                cashier.setPassword(newPassword);
                //CHANGE IN DB HERE==============================
                System.out.println("CASHIER PASSWORD CHANGED SUCCESSFULLY");
                return true;
            }
        }
        System.out.println("CASHIER PASSWORD CHANGE FAILED");
        return false;
    }

    @PostMapping("/changePassword/dataentryoperator")
    public boolean changeDataEntryOperatorPassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword) {
        for (DataEntryOperator dataEntryOperator : dataEntryOperators) {
            if (dataEntryOperator.getEmail().equals(email) && dataEntryOperator.getPassword().equals(oldPassword)) {
                dataEntryOperator.setPassword(newPassword);
                //CHANGE IN DB HERE==============================
                System.out.println("DATA ENTRY OPERATOR PASSWORD CHANGED SUCCESSFULLY");
                return true;
            }
        }
        System.out.println("DATA ENTRY OPERATOR PASSWORD CHANGE FAILED");
        return false;
    }

    public List<BranchManager> getAdmins() {
        return admins;
    }

    public void setAdmins(List<BranchManager> admins) {
        this.admins = admins;
    }

    public List<BranchManagement> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchManagement> branches) {
        this.branches = branches;
    }

    public List<Cashier> getCashiers() {
        return cashiers;
    }

    public void setCashiers(List<Cashier> cashiers) {
        this.cashiers = cashiers;
    }

    public List<DataEntryOperator> getDataEntryOperators() {
        return dataEntryOperators;
    }

    public void setDataEntryOperators(List<DataEntryOperator> dataEntryOperators) {
        this.dataEntryOperators = dataEntryOperators;
    }

    public List<SuperAdmin> getSuperAdmins() {
        return superAdmins;
    }

    public void setSuperAdmins(List<SuperAdmin> superAdmins) {
        this.superAdmins = superAdmins;
    }

    public static void main(String[] args)
    {
        METRO_POS_MAIN_CONTROLLER_CODE controller = new METRO_POS_MAIN_CONTROLLER_CODE();

        controller.loadData();

        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.println("========= WELCOME TO METRO POS SYSTEM =========");
            System.out.println("1. Login as Super Admin");
            System.out.println("2. Login as Branch Manager");
            System.out.println("3. Login as Cashier");
            System.out.println("4. Login as Data Entry Operator");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1:
                    System.out.println("Enter Super Admin Email: ");
                    String superAdminEmail = scanner.nextLine();
                    System.out.println("Enter Super Admin Password: ");
                    String superAdminPassword = scanner.nextLine();
                    SuperAdmin superAdmin = controller.loginSuperAdmin(superAdminEmail, superAdminPassword);

                    if (superAdmin != null)
                    {
                        System.out.println("1. Create Branch");
                        System.out.println("2. Add Branch Manager");
                        System.out.println("3. Add Cashier");
                        System.out.println("4. Add Data Entry Operator");
                        System.out.println("5. Logout");
                        System.out.print("Enter your choice: ");
                        int superAdminChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (superAdminChoice)
                        {
                            case 1:
                                System.out.println("Enter Branch Code: ");
                                String branchCode = scanner.nextLine();
                                System.out.println("Enter Branch Name: ");
                                String branchName = scanner.nextLine();
                                System.out.println("Enter Branch City: ");
                                String branchCity = scanner.nextLine();
                                System.out.println("Enter Branch Address: ");
                                String branchAddress = scanner.nextLine();
                                System.out.println("Enter Branch Phone: ");
                                String branchPhone = scanner.nextLine();
                                System.out.println("Is the branch active? (true/false): ");
                                boolean branchActive = scanner.nextBoolean();
                                superAdmin.createBranch(branchCode, branchName, branchCity, branchActive, branchAddress, branchPhone);
                                break;
                            case 2:
                                System.out.println("Enter Name: ");
                                String adminName = scanner.nextLine();
                                System.out.println("Enter Email: ");
                                String adminEmail = scanner.nextLine();
                                System.out.println("Enter Password: ");
                                String adminPassword = scanner.nextLine();
                                System.out.println("Enter Branch Code: ");
                                String adminBranchCode = scanner.nextLine();
                                System.out.println("Enter Salary: ");
                                double adminSalary = scanner.nextDouble();
                                controller.signUpBranchManager(adminName, adminEmail, adminPassword, adminBranchCode, adminSalary);
                                break;
                            case 3:
                                System.out.println("Enter Name: ");
                                String cashierName = scanner.nextLine();
                                System.out.println("Enter Email: ");
                                String cashierEmail = scanner.nextLine();
                                System.out.println("Enter Password: ");
                                String cashierPassword = scanner.nextLine();
                                System.out.println("Enter Branch Code: ");
                                String cashierBranchCode = scanner.nextLine();
                                System.out.println("Enter Salary: ");
                                double cashierSalary = scanner.nextDouble();
                                controller.signUpCashier(cashierName, cashierEmail, cashierPassword, cashierBranchCode, cashierSalary);
                                break;
                            case 4:
                                System.out.println("Enter Name: ");
                                String dataEntryName = scanner.nextLine();
                                System.out.println("Enter Email: ");
                                String dataEntryEmail = scanner.nextLine();
                                System.out.println("Enter Password: ");
                                String dataEntryPassword = scanner.nextLine();
                                System.out.println("Enter Branch Code: ");
                                String dataEntryBranchCode = scanner.nextLine();
                                System.out.println("Enter Salary: ");
                                double dataEntrySalary = scanner.nextDouble();
                                controller.signUpDataEntryOperator(dataEntryName, dataEntryEmail, dataEntryPassword, dataEntryBranchCode, dataEntrySalary);
                                break;
                            case 5:
                                System.out.println("Logging out...");
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    }
                    break;

                case 2:
                    System.out.println("Enter Branch Manager Email: ");
                    String managerEmail = scanner.nextLine();
                    System.out.println("Enter Branch Manager Password: ");
                    String managerPassword = scanner.nextLine();
                    BranchManager branchManager = controller.loginAdmin(managerEmail, managerPassword);

                    if (branchManager != null) {
                        System.out.println("1. Add Cashier");
                        System.out.println("2. Add Data Entry Operator");
                        System.out.println("3. Logout");
                        System.out.print("Enter your choice: ");
                        int managerChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (managerChoice) {
                            case 1:
                                System.out.println("Enter Name: ");
                                String cashierNameBM = scanner.nextLine();
                                System.out.println("Enter Email: ");
                                String cashierEmailBM = scanner.nextLine();
                                System.out.println("Enter Password: ");
                                String cashierPasswordBM = scanner.nextLine();
                                System.out.println("Enter Salary: ");
                                double cashierSalaryBM = scanner.nextDouble();
                                branchManager.addCashier(cashierNameBM, cashierEmailBM, cashierPasswordBM, cashierSalaryBM);
                                break;
                            case 2:
                                System.out.println("Enter Name: ");
                                String dataEntryNameBM = scanner.nextLine();
                                System.out.println("Enter Email: ");
                                String dataEntryEmailBM = scanner.nextLine();
                                System.out.println("Enter Password: ");
                                String dataEntryPasswordBM = scanner.nextLine();
                                System.out.println("Enter Salary: ");
                                double dataEntrySalaryBM = scanner.nextDouble();
                                branchManager.addDataEntryOperator(dataEntryNameBM, dataEntryEmailBM, dataEntryPasswordBM, dataEntrySalaryBM);
                                break;
                            case 3:
                                System.out.println("Logging out...");
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    }
                    break;

                case 3:
                    System.out.println("Enter Cashier Email: ");
                    String cashierEmailLogin = scanner.nextLine();
                    System.out.println("Enter Cashier Password: ");
                    String cashierPasswordLogin = scanner.nextLine();
                    Cashier cashier = controller.loginCashier(cashierEmailLogin, cashierPasswordLogin);

                    if (cashier != null) {
                        System.out.println("Functionality to be implemented.");
                    }
                    break;

                case 4:
                    System.out.println("Enter Data Entry Operator Email: ");
                    String dataEntryEmailLogin = scanner.nextLine();
                    System.out.println("Enter Data Entry Operator Password: ");
                    String dataEntryPasswordLogin = scanner.nextLine();
                    DataEntryOperator dataEntryOperator = controller.loginDataEntryOperator(dataEntryEmailLogin, dataEntryPasswordLogin);

                    if (dataEntryOperator != null) {
                        System.out.println("Functionality to be implemented.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}