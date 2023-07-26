package com.shop.customer.library.repository;

import com.shop.customer.library.model.Customer;
import com.shop.customer.library.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUserName(String userName);

    Customer findCustomerByShoppingCart(ShoppingCart cart);
}
