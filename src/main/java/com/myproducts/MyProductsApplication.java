package com.myproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class MyProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyProductsApplication.class, args);
	}

}
