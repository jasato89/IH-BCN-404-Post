package com.ironhack.product;

import com.ironhack.product.enums.Category;
import com.ironhack.product.models.Product;
import com.ironhack.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class ProductApplication {

    @Autowired
    ProductRepository productRepository;
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }



}
