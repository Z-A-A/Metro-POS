package org.ZAA.Controller;

import java.util.ArrayList;
import java.util.List;

public class BranchManagement
{
    private Branch branch;
    private List<BranchManager> branchManagers;
    private List<Cashier> cashiers;
    private List<DataEntryOperator> dataEntryOperators;

    public BranchManagement()
    {

    }


    public BranchManagement(Branch branch) {
        this.branch = branch;
        this.branchManagers = new ArrayList<>();
        this.cashiers = new ArrayList<>();
        this.dataEntryOperators = new ArrayList<>();
    }

    public Branch getBranch() {
        return branch;
    }

    public List<BranchManager> getBranchManagers() {
        return branchManagers;
    }

    public List<Cashier> getCashiers() {
        return cashiers;
    }

    public List<DataEntryOperator> getDataEntryOperators() {
        return dataEntryOperators;
    }

    public void addBranchManager(BranchManager branchManager) {
        branchManagers.add(branchManager);
        branch.incrementEmployeeCount();
    }

    public void addCashier(Cashier cashier) {
        cashiers.add(cashier);
        branch.incrementEmployeeCount();
    }

    public void addDataEntryOperator(DataEntryOperator dataEntryOperator) {
        dataEntryOperators.add(dataEntryOperator);
        branch.incrementEmployeeCount();
    }
}
