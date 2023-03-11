package com.shop.library.repository;

import com.shop.library.model.Customer;
import com.shop.library.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUserName(String userName);

    Customer findCustomerByShoppingCart(ShoppingCart cart);
}
