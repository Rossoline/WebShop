package com.shop.library.dto;

import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 15;
    private static final int PASSWORD_MIN_LENGTH = 5;
    private static final int PASSWORD_MAX_LENGTH = 20;
    @Size (min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH,
            message = "First name should have 3-15 characters")
    private String firstName;
    @Size (min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH,
            message = "Last name should have 3-15 characters")
    private String lastName;
    private String userName;
    @Size (min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH,
            message = "Password should have 5-20 characters")
    private String password;
    private String repeatPassword;
}
