package com.shop.library.dto;

import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 15;
    private static final int PASSWORD_MIN_LENGTH = 5;
    private static final int PASSWORD_MAX_LENGTH = 15;
    @Size (min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH,
            message = "Invalid first name! (3-15 characters)")
    private String firstName;
    @Size (min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH,
            message = "Invalid last name! (3-15 characters)")
    private String lastName;
    private String username;
    @Size (min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH,
            message = "Invalid password! (5-15 characters)")
    private String password;
    private String repeatPassword;
}
