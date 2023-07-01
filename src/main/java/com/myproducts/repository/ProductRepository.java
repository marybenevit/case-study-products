package com.myproducts.repository;

import com.myproducts.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM TB_PRODUCT WHERE PRODUCT_NAME = :productName", nativeQuery = true)
    Optional<Product> findByName(String productName);
}
