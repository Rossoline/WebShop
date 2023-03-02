package com.shop.library.dto;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    @Id
    private Long categoryId;
    private String categoryName;
    private Long quantityOfProducts;
}
