package com.shop.library.service.mapper;

public interface ResponseDtoMapper<D, M> {
    D mapToDto(M model);
}
