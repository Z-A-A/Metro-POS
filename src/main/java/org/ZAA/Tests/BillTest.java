package org.ZAA.Tests;

import org.ZAA.Controller.Product;
import org.ZAA.Controller.Bill;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class BillTest {

    @Test
    public void testGenerateBill() {
        List<Product> products = Arrays.asList(
                new Product(1, "Product1", 15.0, 10.0, 5.0, 50.0, "Category1", "Description1", "BR001", "Vendor1", 10),
                new Product(2, "Product2", 25.0, 20.0, 10.0, 100.0, "Category2", "Description2", "BR001", "Vendor2", 20)
        );
        String branchCode = "BR001";

        Bill bill = new Bill();
        Bill generatedBill = bill.generateBill(products, branchCode);

        assertNotNull(generatedBill.getBillNumber());
        assertEquals(branchCode, generatedBill.getBranchCode());
        assertEquals(30.0, generatedBill.getTotalAmount());
    }
}