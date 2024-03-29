package com.shop.customer.library.model;

import com.shop.customer.library.model.enums.ActivationStatus;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "products", uniqueConstraints = @UniqueConstraint (columnNames = {"name", "image"}))
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "product_id")
    private Long id;
    private String name;
    private String description;
    private BigDecimal costPrice;
    private int currentQuantity;
    @Lob
    @Column (columnDefinition = "MEDIUMBLOB")
    private String image;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "category_id", referencedColumnName = "category_id")
    private Category category;
    @Enumerated (EnumType.STRING)
    private ActivationStatus status;
}
