package com.junaid.farm2table.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.junaid.farm2table.Repo.ProductRepository;
import com.junaid.farm2table.model.Product;
import com.junaid.farm2table.service.ImageService;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageService imageService;

    // Get all products (with Base64 encoded image)
    @GetMapping("/api/products")
    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        products.forEach(product -> {
            byte[] imageBytes = product.getImage();
            if (imageBytes != null) {
                String encodedImage = Base64.getEncoder().encodeToString(imageBytes);
                product.setImageBase64(encodedImage);
            }
        });
        return products;
    }

    // Add a new product
    @PostMapping("/api/products")
    public ResponseEntity<String> addProduct(@RequestParam("name") String name,
                                             @RequestParam("description") String description,
                                             @RequestParam("price") double price,
                                             @RequestParam("image") MultipartFile imageFile) throws IOException {

        byte[] imageBytes = imageFile.getBytes();
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage(imageBytes);

        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully!");
    }
}
