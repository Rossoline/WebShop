package com.shop.library.model;

import com.shop.library.model.enums.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admins")
public class Admin {
    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 15;
    private static final int PASSWORD_MIN_LENGTH = 5;
    private static final int PASSWORD_MAX_LENGTH = 15;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH,
            message = "Invalid first name! (3-15 characters)")
    private String firstName;
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH,
            message = "Invalid last name! (3-15 characters)")
    private String lastName;
    private String userName;
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH,
            message = "Invalid password! (5-15 characters)")
    private String password;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    @Enumerated(EnumType.STRING)
    private Role role;
}
