package lk.ijse.poswebbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.poswebbackend.dto.ProductDto;
import lk.ijse.poswebbackend.entity.Product;
import lk.ijse.poswebbackend.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProductsList() {
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
        try {
            return ResponseEntity.status(201).body(productService.createProduct(productDto));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to create the Product");
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);

        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = productService.updateProduct(id, productDto);

        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/categories/{id}/products")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getProductByCategory(id));
    }
    
}
