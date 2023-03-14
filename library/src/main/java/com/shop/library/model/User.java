package com.shop.library.model;

import com.shop.library.model.enums.Role;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class User {
    private String firstName;
    private String lastName;
    @Column (name = "user_name")
    private String userName;
    private String password;
    @Enumerated (EnumType.STRING)
    private Role role;
}
