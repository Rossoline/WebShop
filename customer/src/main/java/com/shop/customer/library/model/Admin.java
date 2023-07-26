package com.shop.customer.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "admins")
public class Admin extends User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "admin_id")
    private Long id;
    @Lob
    @Column (columnDefinition = "MEDIUMBLOB")
    private String image;

}
