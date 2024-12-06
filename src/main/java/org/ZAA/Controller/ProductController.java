package org.ZAA.Controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
@RestController
@RequestMapping("/api/products")
public class ProductController
{

    private final String uploadDir = "src/images"; // Set the directory path here

    public ProductController() {
    }

    @PostMapping("/createProduct")
    public Product createProduct(@RequestParam int id, @RequestParam String name, @RequestParam double originalPrice, @RequestParam double salePrice, @RequestParam double priceByUnit, @RequestParam double priceByCarton, @RequestParam String category, @RequestParam String description, @RequestParam String branchCode, @RequestParam int vendorId, @RequestParam int quantity, @RequestParam("image") MultipartFile image) {
        String imagePath = saveImage(image);
        Product newProduct = new Product(id, name, originalPrice, salePrice, priceByUnit, priceByCarton, category, description, branchCode, vendorId, quantity, imagePath);
        // ALSO ADD IN DATABASE HERE ===============================
        System.out.println("PRODUCT ADDED SUCCESSFULLY: " + newProduct.getName());
        return newProduct;
    }

    private String saveImage(MultipartFile image) {
        if (image.isEmpty()) {
            throw new RuntimeException("No image provided");
        }

        try {
            String fileName = image.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.write(filePath, image.getBytes());
            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image", e);
        }
    }

    @PostMapping("/updateProduct")
    public Product updateProduct(@RequestParam int id, @RequestParam String name, @RequestParam double originalPrice, @RequestParam double salePrice, @RequestParam double priceByUnit, @RequestParam double priceByCarton, @RequestParam int category, @RequestParam String description, @RequestParam String branchCode, @RequestParam int vendorId, @RequestParam int quantity) {
        // Fetch the product from the database using the id
        // Product existingProduct = fetchProductById(id);

        // Update the product details
        // existingProduct.setOriginalPrice(originalPrice);
        // existingProduct.setSalePrice(salePrice);
        // existingProduct.setPricebyUnit(priceByUnit);
        // existingProduct.setPricebyCarton(priceByCarton);
        // existingProduct.setCategory(category);

        // Save the updated product back to the database
        // saveProduct(existingProduct);

        // System.out.println("PRODUCT UPDATED SUCCESSFULLY: " + existingProduct.getName());
        // return existingProduct;
        return null;
    }

    @PostMapping("/getProductByBranchCode")
    public List<Product> getProductByBranchCode(@RequestParam String branchCode) {
        // Fetch products from the database using the branch code
        // List<Product> products = fetchProductsByBranchCode(branchCode);
        // System.out.println("PRODUCTS FETCHED SUCCESSFULLY FOR BRANCH: " + branchCode);
        // return products;

        return null;
    }
}