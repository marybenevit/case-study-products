package com.myproducts.resources;

import com.myproducts.model.Product;
import com.myproducts.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductResource {

    @Autowired
    private ProductService service;

    private static final Logger logger = LoggerFactory.getLogger(ProductResource.class);

    @GetMapping("/list")
    private ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = this.service.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productName}")
    private ResponseEntity<Product> getProductByName(@PathVariable("productName") String productName) {
        Product product = this.service.getProductByName(productName);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/new")
    private ResponseEntity<String> createProduct(@RequestBody Product product) {
        try {
            this.service.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/edit")
    private ResponseEntity<String> editProduct(@RequestBody Product product) {
        try {
            this.service.updateProduct(product);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{productName}")
    private ResponseEntity<String> deleteProduct(@PathVariable("productName") String productName) {
        try {
            this.service.deleteProductByName(productName);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
