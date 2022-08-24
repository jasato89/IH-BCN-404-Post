package com.ironhack.product.services;

import com.ironhack.product.dtos.PriceUpdateDTO;
import com.ironhack.product.models.Product;
import com.ironhack.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public Product updateValues(PriceUpdateDTO priceUpdateDTO) {
        Product product = productRepository.findById(priceUpdateDTO.getId()).get();
        product.setPrice(priceUpdateDTO.getPrice());
        return productRepository.save(product);
    }

    public Product saveProduct(Long id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Product findProductById(Long id) {
        if (productRepository.findById(id).isPresent()) {
            return productRepository.findById(id).get();
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "A product with this ID doesn't exits in the database");
    }

    public Product findProductByIdShortVersion(Long id)  {
        return productRepository.findById(id).orElseThrow(() ->
                throwException("A product with this ID doesn't exits in the database")
        );
    }



    public ResponseStatusException throwException(String message) {
       return new ResponseStatusException(
                HttpStatus.NOT_FOUND, message);
    }
}
