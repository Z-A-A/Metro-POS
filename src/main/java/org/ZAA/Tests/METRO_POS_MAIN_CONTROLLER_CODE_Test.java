package org.ZAA.Tests;

import org.ZAA.Controller.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class METRO_POS_MAIN_CONTROLLER_CODE_Test {

    private METRO_POS_MAIN_CONTROLLER_CODE controller;

    @BeforeEach
    public void setUp() {
        controller = new METRO_POS_MAIN_CONTROLLER_CODE();
        controller.loadData();
    }

    @Test
    public void testLoginSuperAdmin() {
        SuperAdmin superAdmin = controller.loginSuperAdmin("admin1@example.com", "admin123");
        assertNotNull(superAdmin);
        assertEquals("Admin1", superAdmin.getName());
    }

    @Test
    public void testLoginBranchManager() {
        BranchManager branchManager = controller.loginAdmin("alice@example.com", "Password_123");
        assertNotNull(branchManager);
        assertEquals("Alice", branchManager.getName());
    }

    @Test
    public void testLoginCashier() {
        Cashier cashier = controller.loginCashier("john@example.com", "Password_123");
        assertNotNull(cashier);
        assertEquals("John", cashier.getName());
    }

    @Test
    public void testLoginDataEntryOperator() {
        DataEntryOperator dataEntryOperator = controller.loginDataEntryOperator("charlie@example.com", "Password_123");
        assertNotNull(dataEntryOperator);
        assertEquals("Charlie", dataEntryOperator.getName());
    }

    @Test
    public void testSignUpBranchManager() {
        boolean result = controller.signUpBranchManager("NewManager", "newmanager@example.com", "newpassword", "B001", 60000);
        assertTrue(result);
    }

    @Test
    public void testSignUpCashier() {
        boolean result = controller.signUpCashier("NewCashier", "newcashier@example.com", "newpassword", "B001", 35000);
        assertTrue(result);
    }

    @Test
    public void testSignUpDataEntryOperator() {
        boolean result = controller.signUpDataEntryOperator("NewDataEntry", "newdataentry@example.com", "newpassword", "B001", 28000);
        assertTrue(result);
    }
}