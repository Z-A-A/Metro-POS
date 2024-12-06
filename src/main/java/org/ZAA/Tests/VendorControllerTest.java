package org.ZAA.Tests;

import org.ZAA.Controller.Vendor;
import org.ZAA.Controller.VendorController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class VendorControllerTest {

    private VendorController vendorController;

    @BeforeEach
    public void setUp() {
        vendorController = new VendorController();
    }

    @Test
    public void testCreateVendor() {
        Vendor vendor = vendorController.createVendor(1, "Vendor1", "123 Vendor St", "123-456-7890", "BR001");
        assertNotNull(vendor);
        assertEquals(1, vendor.getVendorID());
        assertEquals("Vendor1", vendor.getVendorName());
        assertEquals("123 Vendor St", vendor.getVendorAddress());
        assertEquals("123-456-7890", vendor.getVendorPhone());
        assertEquals("BR001", vendor.getBranchCode());
    }

    @Test
    public void testGetAllVendors() {
        List<Vendor> vendors = vendorController.getAllVendors();
        assertNull(vendors); // Since the method returns null
    }
}