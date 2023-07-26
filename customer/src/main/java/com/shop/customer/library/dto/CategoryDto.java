package com.shop.customer.library.dto;

import com.shop.customer.library.model.enums.ActivationStatus;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    @Id
    private Long id;
    private String name;
    private ActivationStatus activationStatus;
}
