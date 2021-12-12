package com.restuarant.restuarant.wishlist.repository;

import com.restuarant.restuarant.wishlist.entity.WishListEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishListRepositoryTest {

    @Autowired
    private WishListRepository wishListRepository;


    public WishListEntity create(){
        WishListEntity wishListEntity  = new WishListEntity();
        wishListEntity.setTitle("맛집");
        wishListEntity.setCategory("한식");
        wishListEntity.setAddress("");
        wishListEntity.setHomepageLink("");
        wishListEntity.setImageLink("");
        wishListEntity.setVisit(false);
        wishListEntity.setVisitCount(0);
        wishListEntity.setLastVisitDate(null);
        return wishListEntity;
    }

    @Test
    public void saveTest(){
        WishListEntity wishListEntity = create();
        var expected = wishListRepository.save(wishListEntity);
        Assertions.assertNotNull(expected);
        Assertions.assertEquals(1,expected.getIndex());
    }
    @Test
    public void findByIdTest(){
        WishListEntity wishListEntity = create();
        wishListRepository.save(wishListEntity);

        var expected = wishListRepository.findById(1);
        Assertions.assertEquals(true,expected.isPresent());
        Assertions.assertEquals(1,expected.get().getIndex());
    }
    @Test
    public void deleteTest(){
        WishListEntity wishListEntity = create();
        wishListRepository.save(wishListEntity);

        wishListRepository.deleteById(1);
        int count = wishListRepository.listAll().size();

        Assertions.assertEquals(0,count);
    }
    @Test
    public void listAllTest(){
        WishListEntity wishListEntity = create();
        wishListRepository.save(wishListEntity);

        var expected = wishListRepository.listAll().size();

        Assertions.assertEquals(33,expected);
    }
}
