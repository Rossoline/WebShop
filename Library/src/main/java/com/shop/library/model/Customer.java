package com.shop.library.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers",
        uniqueConstraints = @UniqueConstraint(columnNames = {"username", "image", "phone_number"}))
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    @Size(min = 3, max = 15, message = "First name should have 3-15 characters")
    private String firstName;
    @Size(min = 3, max = 15, message = "Last name should have 3-15 characters")
    private String lastName;
    private String userName;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String password;
    @Column(name = "city")
    private String city;
    private String address;
    @Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
    private String image;
    @OneToOne(mappedBy = "customer")
    private ShoppingCart shoppingCart;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
    @Enumerated(EnumType.STRING)
    private Role role;
}
