package com.ironhack.product.controller.impl;

import com.ironhack.product.controller.ProductControllerInt;
import com.ironhack.product.dtos.PriceUpdateDTO;
import com.ironhack.product.models.Product;
import com.ironhack.product.repositories.ProductRepository;
import org.hibernate.validator.constraints.ModCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductController  {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/update")
    public Product updateValues(@RequestBody PriceUpdateDTO priceUpdateDTO) {
        Product product = productRepository.findById(priceUpdateDTO.getId()).get();
        product.setPrice(priceUpdateDTO.getPrice());
        return productRepository.save(product);
    }

    @PostMapping("/save-product/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Product saveProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    @PutMapping("/save-product")
    @ResponseStatus(value = HttpStatus.OK)
    public Product updateProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PatchMapping("/save-product")
    @ResponseStatus(value = HttpStatus.OK)
    public Product patchProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @DeleteMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
