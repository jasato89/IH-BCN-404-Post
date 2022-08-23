package com.ironhack.product.models;

import com.ironhack.product.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

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
    @Digits(integer = 6, fraction = 2)
    @DecimalMin(value = "0.0", message = "Too Low")
    @DecimalMax(value = "1000.0", message = "Too high")
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    //@NotNull(message = "A category must be given")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Department department;

}
