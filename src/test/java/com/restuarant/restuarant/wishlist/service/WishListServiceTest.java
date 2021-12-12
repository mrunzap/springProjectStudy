package com.restuarant.restuarant.wishlist.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishListServiceTest {

    @Autowired
    private WishListService wishListService;

    @Test
    public void search(){
        var result = wishListService.search("아이유");
        System.out.println(result);
        Assertions.assertNotNull(result,"값이 없습니다.");

    }
}
