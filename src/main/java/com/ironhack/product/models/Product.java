package com.ironhack.product.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ironhack.product.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@DynamicUpdate
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotEmpty(message = "Name must be set to create a product")
    private String name;
    //@Digits(integer = 6, fraction = 2)
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    //@NotNull(message = "A category must be given")
    private Category category;
    //@JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate launchDate;




}
