package com.ironhack.product.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.google.gson.Gson;
import com.ironhack.product.enums.Category;
import com.ironhack.product.models.Product;
import com.ironhack.product.repositories.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    ProductRepository productRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    Product product;
    Gson gson = new Gson();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper.findAndRegisterModules();
        product = productRepository.save(
                new Product(1L, "Zelda Breath of the Wild", new BigDecimal(25.8),
                        Category.DIGITAL));


    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    void testProductExists() {
        System.out.println(productRepository.findAll().get(0).getId());
        assertEquals("Zelda Breath of the Wild", productRepository.findAll().get(0).getName());
    }

    @Test
    public void checkGetAllMethodCreate_works() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/products"))
                .andExpect(status().isOk()).andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Breath of the Wild"));

    }

    @Test
    public void checkPostMethod_works() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.now();

        Product product1 = new Product(1L, "Super Mario Kart", new BigDecimal(25.8),
                Category.DIGITAL);

        String body = gson.toJson(product1);

        System.out.println(body);

        MvcResult mvcResult = mockMvc.perform(post("/save-product")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Super Mario"));


    }

    @Test
    void checkIdDoesntExist_throwsError() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/find/1233123"))
                .andExpect(status().isNotFound())
                .andReturn();

        assertTrue(mvcResult.getResolvedException()
                .getMessage()
                .contains("A product with this ID doesn't exits in the database"));


    }

}
