package com.myproducts.service;

import com.myproducts.model.Product;
import com.myproducts.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public List<Product> getAllProducts() {
        List<Product> products = this.repository.findAll();
        if (products.isEmpty()) {
            logger.info("There are currently no products to be listed");
        }
        return products;
    }

    public Product getProductById(Long productId) {
        return this.repository.findById(productId).orElse(null);
    }

    public Product getProductByName(String productName) {
        return this.repository.findByName(productName).orElse(null);
    }

    @Transactional
    public void createProduct(Product product) {
        Product existingProduct = this.getProductByName(product.getProductName());
        if (existingProduct == null) {
            this.repository.save(product);
            logger.info("New department created with the name " + product.getProductName());
        }
        else {
            throw new IllegalArgumentException("Product with the name " + product.getProductName() + " already exists");
        }
    }

    @Transactional
    public void updateProduct(Product product) {
        Product existingProduct = this.getProductById(product.getProductId());
        if (existingProduct != null) {
            this.repository.save(product);
            logger.info("Product with the name " + product.getProductName() + " was updated");
        }
        else {
            throw new IllegalArgumentException("Product with the name " + product.getProductName() + " doesn't exist");
        }
    }

    @Transactional
    public void deleteProductByName(String productName) {
        Product existingProduct = this.getProductByName(productName);
        if (existingProduct != null) {
            this.repository.deleteById(existingProduct.getProductId());
        }
        else {
            throw new IllegalArgumentException("Product with the name " + productName + " doesn't exist");
        }
    }
}
