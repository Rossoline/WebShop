package com.shop.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    @Id

    private Long categoryId;
    private String categoryName;
    private Long quantityOfProducts;
}
