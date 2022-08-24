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
import java.time.LocalDateTime;

@Entity
@DynamicUpdate

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
