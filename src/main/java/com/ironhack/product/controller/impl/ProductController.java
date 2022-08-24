package com.ironhack.product.controller.impl;

import com.ironhack.product.dtos.PriceUpdateDTO;
import com.ironhack.product.models.Product;
import com.ironhack.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController  {

    @Autowired
    ProductService productService;


    @GetMapping("/find/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @PostMapping("/update")
    public Product updateValues(@RequestBody PriceUpdateDTO priceUpdateDTO) {
       return productService.updateValues(priceUpdateDTO);
    }

    @PostMapping("/save-product/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Product saveProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.saveProduct(id, product);
    }

    @PutMapping("/save-product")
    @ResponseStatus(value = HttpStatus.OK)
    public Product updateProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @PostMapping("/save-product")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Product patchProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @DeleteMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }
}
