package com.alanpatrik.createproducts.modules.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    private Long id;
    private String name;
    private double price;
    private int quantity;
    private LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;
}
