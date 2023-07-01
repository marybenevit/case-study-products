package com.myproducts.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_PRODUCT")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "productPrice", nullable = false)
    private Double productPrice;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;

    public Product() {
    }

    public Product(Long productId, String productName, Double productPrice, Department department) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.department = department;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", department=" + department +
                '}';
    }
}
