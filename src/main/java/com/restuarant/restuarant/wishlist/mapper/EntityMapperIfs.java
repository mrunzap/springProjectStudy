package com.restuarant.restuarant.wishlist.mapper;

public interface EntityMapperIfs<D,E> {
    E toEntity(D dto);
    D toDto(E entity);
}
