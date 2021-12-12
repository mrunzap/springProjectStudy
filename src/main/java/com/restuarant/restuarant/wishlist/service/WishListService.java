package com.restuarant.restuarant.wishlist.service;

import com.restuarant.restuarant.naver.NaverClient;
import com.restuarant.restuarant.naver.dto.SearchImageReq;
import com.restuarant.restuarant.naver.dto.SearchLocalReq;
import com.restuarant.restuarant.wishlist.dto.WishListDto;
import com.restuarant.restuarant.wishlist.entity.WishListEntity;
import com.restuarant.restuarant.wishlist.mapper.EntityMapperImplIfs;
import com.restuarant.restuarant.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WishListService {

    @Autowired
    private NaverClient naverClient;
    @Autowired
    private WishListRepository wishListRepository;

    public WishListDto search(String query){
        //지역검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        var searchLocal = naverClient.searchUrl(searchLocalReq);

        //이미지검색
        if(searchLocal.getTotal() > 0){
            var item = searchLocal.getItems().stream().findFirst().get();
            var imageItem = item.getTitle().replaceAll("<[^>]*>","");
            var searchImageReqItem = new SearchImageReq();
            searchImageReqItem.setQuery(imageItem);
            searchImageReqItem.setFilter("large");
            var searchImage = naverClient.searchImage(searchImageReqItem);
            if(searchImage.getTotal() > 0){

                var dto = new WishListDto();
                dto.setTitle(item.getTitle());
                dto.setCategory(item.getCategory());
                dto.setAddress(item.getRoadAddress());
                dto.setHomepageLink(item.getLink());
                dto.setReadAddress(item.getRoadAddress());
                dto.setImageLink(searchImage.getItems().stream().findFirst().get().getLink());

                //결과 리턴
                return dto;

            }
        }
        return new WishListDto();




    }

    public WishListDto add(WishListDto wishListDto) {
        // 데이터베이스에 저장.
        var entity = EntityMapperImplIfs.MAPPER.toEntity(wishListDto);
        log.info("ToEntity {}",entity);
        wishListRepository.save(entity);
        return EntityMapperImplIfs.MAPPER.toDto(entity);
    }

    public List<WishListDto> findAll() {
        return wishListRepository.listAll()
                                 .stream()
                                 .map(it -> EntityMapperImplIfs.MAPPER.toDto(it))
                .collect(Collectors.toList());
    }

    public void delete(int index) {
        wishListRepository.deleteById(index);
    }


    public void addVisit(int index) {
        var item = wishListRepository.findById(index);
        if (item.isPresent()){
            var it  = item.get();
            it.setVisit(true);
            it.setVisitCount(it.getVisitCount() + 1);
        }
    }

    public void deleteAll() {
        wishListRepository.deleteAll();
    }
}
