package com.shop.admin.lib.dto;

import com.shop.admin.lib.model.enums.ActivationStatus;
import java.math.BigDecimal;
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
    private BigDecimal costPrice;
    private int currentQuantity;
    private String image;
    private CategoryDto categoryDto;
    private ActivationStatus status;
}
