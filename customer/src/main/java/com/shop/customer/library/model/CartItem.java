package com.shop.customer.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table (name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "cart_item_id")
    private Long id;
    private int quantity;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "product_id", referencedColumnName = "product_id")
    private Product product;
}
