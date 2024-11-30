package org.ZAA.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Component
@RestController
@RequestMapping("/api/products")
public class ProductController
{
    public ProductController()
    {

    }

    @PostMapping("/createProduct")
    public Product createProduct(@RequestParam int id, @RequestParam String name, @RequestParam double originalPrice, @RequestParam double salePrice, @RequestParam double priceByUnit, @RequestParam double priceByCarton, @RequestParam String category, @RequestParam String description, @RequestParam String branchCode, @RequestParam String vendorCode, @RequestParam int quantity) {
        Product newProduct = new Product(id, name, originalPrice, salePrice, priceByUnit, priceByCarton, category, description, branchCode, vendorCode, quantity);
        // ALSO ADD IN DATABASE HERE ===============================
        System.out.println("PRODUCT ADDED SUCCESSFULLY: " + newProduct.getName());
        return newProduct;
    }

    @PostMapping("/updateProduct")
    public Product updateProduct(@RequestParam int id, @RequestParam String name, @RequestParam double originalPrice, @RequestParam double salePrice, @RequestParam double priceByUnit, @RequestParam double priceByCarton, @RequestParam int category, @RequestParam String description, @RequestParam String branchCode, @RequestParam String vendorCode, @RequestParam int quantity)
    {
//        // Fetch the product from the database using the id
//        Product existingProduct = fetchProductById(id);
//
//        // Update the product details
//          existingProduct.setOriginalPrice(originalPrice);
//          existingProduct.setSalePrice(salePrice);
//          existingProduct.setPricebyUnit(priceByUnit);
//          existingProduct.setPricebyCarton(priceByCarton);
//          existingProduct.setCategory(category);
//
//        // Save the updated product back to the database
//        saveProduct(existingProduct);
//
//        System.out.println("PRODUCT UPDATED SUCCESSFULLY: " + existingProduct.getName());
//        return existingProduct;
        return null;
    }

    @PostMapping("/getProductByBranchCode")
    public List<Product> getProductByBranchCode(@RequestParam String branchCode)
    {
        // Fetch products from the database using the branch code
//        List<Product> products = fetchProductsByBranchCode(branchCode);
//        System.out.println("PRODUCTS FETCHED SUCCESSFULLY FOR BRANCH: " + branchCode);
//        return products;

        return null;
    }

}
