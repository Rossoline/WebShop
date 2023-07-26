package com.shop.admin.lib.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "customers",
        uniqueConstraints = @UniqueConstraint (columnNames = {"user_name", "phone_number"}))
public class Customer extends User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "customer_id")
    private Long id;
    @Column (name = "phone_number")
    @Builder.Default
    private String phoneNumber = "";
    @Column (name = "city")
    @Builder.Default
    private String city = "";
    @Column (name = "address")
    @Builder.Default
    private String address = "";
    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "shopping_cart_id")
    private ShoppingCart shoppingCart;
    @OneToMany (mappedBy = "customer")
    private List<Order> orders;
}
