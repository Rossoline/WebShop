package com.shop.admin.lib.repository;

import com.shop.admin.lib.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUserName(String userName);
}
