package com.restuarant.restuarant.wishlist.repository;

import com.restuarant.restuarant.db.MemoryDBRepositoryAbstract;
import com.restuarant.restuarant.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository extends MemoryDBRepositoryAbstract<WishListEntity> {
}
