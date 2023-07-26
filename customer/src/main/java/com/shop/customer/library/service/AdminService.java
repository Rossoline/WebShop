package com.shop.customer.library.service;

import com.shop.customer.library.dto.AdminDto;
import com.shop.customer.library.model.Admin;

public interface AdminService {
    Admin findByUsername(String username);

    Admin save(AdminDto adminDto);
}
