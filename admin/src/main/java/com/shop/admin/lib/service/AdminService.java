package com.shop.admin.lib.service;

import com.shop.admin.lib.dto.AdminDto;
import com.shop.admin.lib.model.Admin;

public interface AdminService {
    Admin findByUsername(String username);

    Admin save(AdminDto adminDto);
}
