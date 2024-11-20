package org.ZAA.Controller;


import java.util.List;

public class SuperAdmin
{
    private String name;
    private String email;
    private String password;

    private List<BranchManagement> branchManagementList;

    public SuperAdmin(String name, String email, String password)
    {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setBranchManagementList(List<BranchManagement> branchManagementList) {
        this.branchManagementList = branchManagementList;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void createBranch(String branchCode, String name, String city, boolean isActive, String address, String phone)
    {
        Branch newBranch = new Branch(branchCode, name, city, isActive, address, phone);
        BranchManagement newBranchManagement = new BranchManagement(newBranch);
        branchManagementList.add(newBranchManagement);
        System.out.println("BRANCH CREATED SUCCESSFULLY: " + name);
    }


}
