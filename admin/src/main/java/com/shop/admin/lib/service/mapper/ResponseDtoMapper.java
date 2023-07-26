package com.shop.admin.lib.service.mapper;

public interface ResponseDtoMapper<D, M> {
    D mapToDto(M model);
}
