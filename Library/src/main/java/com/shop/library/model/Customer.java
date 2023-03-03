package com.shop.library.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers",
        uniqueConstraints = @UniqueConstraint(columnNames = {"username", "phone_number"}))
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
    private String password;
    @Column(name = "phone_number")
    @Builder.Default
    private String phoneNumber = "";
    @Column(name = "city")
    @Builder.Default
    private String city = "";
    @Column(name = "address")
    @Builder.Default
    private String address = "";
    @JoinColumn(name = "cart_id")
    @OneToOne(fetch = FetchType.EAGER)
    private ShoppingCart shoppingCart;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "customer_id")
    private List<Order> orders;
    @Enumerated(EnumType.STRING)
    private Role role;
}
