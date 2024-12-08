package org.ZAA.Controller;

import org.ZAA.backend.Controller.VendorControllerDb;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@RestController
@RequestMapping("/api/vendors")
public class VendorController
{
    public VendorController()
    {

    }


    @PostMapping("/createVendor")
    public Vendor createVendor(@RequestParam int vendorID, @RequestParam String vendorName, @RequestParam String vendorAddress, @RequestParam String vendorPhone, @RequestParam String branchCode) {
        Vendor newVendor = new Vendor(vendorID, vendorName, vendorAddress, vendorPhone, branchCode);
        // ALSO ADD IN DATABASE HERE ===============================
        VendorControllerDb.addVendor(newVendor);
        System.out.println("VENDOR ADDED SUCCESSFULLY: " + newVendor.getVendorName());
        return newVendor;
    }

    @PostMapping("/getAllVendors")
    public List<Vendor> getAllVendors()
    {
        // Fetch all vendors from the database
        VendorControllerDb vendorControllerDb = new VendorControllerDb();
        List<Vendor> vendors = vendorControllerDb.getAllVendors();
//        List<Vendor> vendors = fetchAllVendors();
//        System.out.println("VENDORS FETCHED SUCCESSFULLY");
        return vendors;

    }


}