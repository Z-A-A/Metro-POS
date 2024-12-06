package org.ZAA.Tests;

import org.ZAA.Controller.Branch;
import org.ZAA.Controller.BranchManagement;
import org.ZAA.Controller.BranchManager;
import org.ZAA.Controller.METRO_POS_MAIN_CONTROLLER_CODE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class BranchManagerTest
{

    private BranchManager branchManager;
    private METRO_POS_MAIN_CONTROLLER_CODE mainController;
    private Branch branch;

    @BeforeEach
    public void setUp() {
        mainController = new METRO_POS_MAIN_CONTROLLER_CODE();
        mainController.setCashiers(new ArrayList<>());
        mainController.setDataEntryOperators(new ArrayList<>());
        mainController.setBranches(new ArrayList<>());

        branch = new Branch("BR001", "Main Branch", "City1", true, "123 Main St", "123-456-7890");
        BranchManagement branchManagement = new BranchManagement(branch);
        mainController.getBranches().add(branchManagement);

        branchManager = new BranchManager("Manager1", 1, "manager1@example.com", "BR001", 5000.0);
        branchManager.setMainController(mainController);
    }

    @Test
    public void testAddCashier() {
        boolean result = branchManager.addCashier("Cashier1", "cashier1@example.com", "password", 3000.0);
        assertTrue(result);
        assertEquals(1, mainController.getCashiers().size());
        assertEquals("Cashier1", mainController.getCashiers().get(0).getName());
        assertEquals(1, branch.getNumberOfEmployees());
    }

    @Test
    public void testAddDataEntryOperator() {
        boolean result = branchManager.addDataEntryOperator("DataEntry1", "dataentry1@example.com", "password", 2500.0);
        assertTrue(result);
        assertEquals(1, mainController.getDataEntryOperators().size());
        assertEquals("DataEntry1", mainController.getDataEntryOperators().get(0).getName());
        assertEquals(1, branch.getNumberOfEmployees());
    }
}