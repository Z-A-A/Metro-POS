package org.ZAA.Tests;

import org.ZAA.Controller.Branch;
import org.ZAA.Controller.BranchManagement;
import org.ZAA.Controller.METRO_POS_MAIN_CONTROLLER_CODE;
import org.ZAA.Controller.SuperAdmin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SuperAdminTest {

    private SuperAdmin superAdmin;
    private METRO_POS_MAIN_CONTROLLER_CODE mainController;

    @BeforeEach
    public void setUp() {
        mainController = new METRO_POS_MAIN_CONTROLLER_CODE();
        mainController.setBranches(new ArrayList<>());
        superAdmin = new SuperAdmin("Admin1", "admin1@example.com", "admin123");
        superAdmin.setMainController(mainController);
    }

    @Test
    public void testCreateBranch() {
        boolean result = superAdmin.createBranch("BR001", "Main Branch", "City1", true, "123 Main St", "123-456-7890");
        assertTrue(result);
        assertEquals(1, mainController.getBranches().size());
        BranchManagement branchManagement = mainController.getBranches().get(0);
        Branch branch = branchManagement.getBranch();
        assertEquals("BR001", branch.getBranchCode());
        assertEquals("Main Branch", branch.getName());
        assertEquals("City1", branch.getCity());
        assertTrue(branch.isActive());
        assertEquals("123 Main St", branch.getAddress());
        assertEquals("123-456-7890", branch.getPhone());
    }
}