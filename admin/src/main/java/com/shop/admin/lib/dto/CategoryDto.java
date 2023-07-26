package com.shop.admin.lib.dto;

import com.shop.admin.lib.model.enums.ActivationStatus;
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
