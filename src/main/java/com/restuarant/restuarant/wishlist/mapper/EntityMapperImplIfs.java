package com.restuarant.restuarant.wishlist.mapper;

import com.restuarant.restuarant.wishlist.dto.WishListDto;
import com.restuarant.restuarant.wishlist.entity.WishListEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntityMapperImplIfs extends EntityMapperIfs<WishListDto, WishListEntity> {

    EntityMapperImplIfs MAPPER = Mappers.getMapper(EntityMapperImplIfs.class);

    @Override
    WishListEntity toEntity(WishListDto dto);

    @Override
    WishListDto toDto(WishListEntity entity);
}
