package com.shop.customer.library.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "shopping_cart_id")
    private Long id;
    @OneToMany
    private Set<CartItem> cartItems;
}
