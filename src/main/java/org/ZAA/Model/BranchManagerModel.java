package org.ZAA.Model;

public class BranchManagerModel {
    private String branchName;
    private String branchAddress;
    private String branchPhone;
    private String branchEmail;
    private String branchManagerName;
    private String branchManagerPhone;
    private String branchManagerEmail;

    public BranchManagerModel(String branchName, String branchAddress, String branchPhone, String branchEmail, String branchManagerName, String branchManagerPhone, String branchManagerEmail) {
        this.branchName = branchName;
        this.branchAddress = branchAddress;
        this.branchPhone = branchPhone;
        this.branchEmail = branchEmail;
        this.branchManagerName = branchManagerName;
        this.branchManagerPhone = branchManagerPhone;
        this.branchManagerEmail = branchManagerEmail;
    }

    public BranchManagerModel() {
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getBranchPhone() {
        return branchPhone;
    }

    public void setBranchPhone(String branchPhone) {
        this.branchPhone = branchPhone;
    }

    public String getBranchEmail() {
        return branchEmail;
    }

    public void setBranchEmail(String branchEmail) {
        this.branchEmail = branchEmail;
    }

    public String getBranchManagerName() {
        return branchManagerName;
    }

    public void setBranchManagerName(String branchManagerName) {
        this.branchManagerName = branchManagerName;
    }

    public String getBranchManagerPhone() {
        return branchManagerPhone;
    }

    public void setBranchManagerPhone(String branchManagerPhone) {
        this.branchManagerPhone = branchManagerPhone;
    }

    public String getBranchManagerEmail() {
        return branchManagerEmail;
    }

    public void setBranchManagerEmail(String branchManagerEmail) {
        this.branchManagerEmail = branchManagerEmail;
    }
}
