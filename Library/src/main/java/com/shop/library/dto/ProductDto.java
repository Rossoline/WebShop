package com.shop.library.dto;

import com.shop.library.model.Category;
import com.shop.library.model.enums.ActivationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private double costPrice;
    private double salePrice;
    private int currentQuantity;
    private Category category;
    private String image;
    private ActivationStatus status;
}
