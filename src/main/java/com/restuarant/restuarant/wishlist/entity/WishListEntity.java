package com.restuarant.restuarant.wishlist.entity;

import com.restuarant.restuarant.db.MemoryDBEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WishListEntity  extends MemoryDBEntity {

    private String title;
    private String category;
    private String address;
    private String readAddress;
    private String homepageLink;
    private String imageLink;
    private boolean isVisit;
    private int visitCount;
    private LocalDateTime lastVisitDate;
}
