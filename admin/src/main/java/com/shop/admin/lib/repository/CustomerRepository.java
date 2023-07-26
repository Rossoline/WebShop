package com.shop.admin.lib.repository;

import com.shop.admin.lib.model.Customer;
import com.shop.admin.lib.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUserName(String userName);

    Customer findCustomerByShoppingCart(ShoppingCart cart);
}
