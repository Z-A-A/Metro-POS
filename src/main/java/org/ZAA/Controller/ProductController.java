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
public class ProductController {

    private final String uploadDir = "src/images"; // Set the directory path here

    public ProductController() {
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

    private byte[] loadImage(String imagePath) {
        try {
            Path path = Paths.get(imagePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image", e);
        }
    }

    @PostMapping("/createProduct")
    public Product createProduct(@RequestParam int id, @RequestParam String name, @RequestParam double originalPrice, @RequestParam double salePrice, @RequestParam double priceByUnit, @RequestParam double priceByCarton, @RequestParam String category, @RequestParam String description, @RequestParam String branchCode, @RequestParam int vendorId, @RequestParam int quantity, @RequestParam("image") MultipartFile image) throws IOException {
        String imagePath = saveImage(image);
        Product newProduct = new Product(id, name, originalPrice, salePrice, priceByUnit, priceByCarton, category, description, branchCode, vendorId, quantity, image.getBytes(),imagePath);
        // ALSO ADD IN DATABASE HERE ===============================
        org.ZAA.backend.Controller.ProductController.addProduct(newProduct);
        System.out.println("PRODUCT ADDED SUCCESSFULLY: " + newProduct.getName());
        return newProduct;
    }

    @PostMapping("/getProductByBranchCode")
    public List<Product> getProductByBranchCode(@RequestParam String branchCode) {
        // Fetch products from the database using the branch code
        List<Product> products= org.ZAA.backend.Controller.ProductController.getProductByBranchCode(branchCode);
       for (Product product : products) {
         byte[] imageData = loadImage(product.getImagePath());
           product.setImage(imageData);
        }
       System.out.println("PRODUCTS FETCHED SUCCESSFULLY FOR BRANCH: " + branchCode);
        return products;
    }

    @PostMapping("/getProductByVendorId")
    public List<Product> getProductByVendorId(@RequestParam String branchCode, @RequestParam int vendorId) {
        // Fetch products from the database using the vendor ID
        List<Product> products = org.ZAA.backend.Controller.ProductController.getProductByVendorId(vendorId, branchCode);
        for (Product product : products) {
           byte[] imageData = loadImage(product.getImagePath());
           product.setImage(imageData);
            System.out.println(product.getName());
        }
        System.out.println("PRODUCTS FETCHED SUCCESSFULLY FOR VENDOR: " + vendorId);

        return products;
    }
    @PostMapping("/updateProductQuantity")
    public Boolean updateProductQuantity(@RequestParam int id, @RequestParam int quantity) {
        // Update the product quantity in the database
        boolean isUpdated =org.ZAA.backend.Controller.ProductController.updateProductStock(id, quantity);
        if(isUpdated)
        {
            System.out.println("PRODUCT QUANTITY UPDATED SUCCESSFULLY");
            return true;
        }
        else{
            System.out.println("PRODUCT QUANTITY NOT UPDATED SUCCESSFULLY");
            return false;
        }
    }





}