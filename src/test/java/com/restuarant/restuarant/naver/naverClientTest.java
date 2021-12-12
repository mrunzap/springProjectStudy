package com.restuarant.restuarant.naver;

import com.restuarant.restuarant.naver.dto.SearchImageReq;
import com.restuarant.restuarant.naver.dto.SearchLocalReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class naverClientTest {

    // Autowired  Ioc Container에 Bean등록하는것
    // 일반적으로 setter 메서드 생성자로 등록하지만 스프링에서는 Autowired로 설정할 수 있다.
    //  Test코드 작성시 용이함.
    @Autowired
    private NaverClient naverClient;

    @Test
    public void seasrchTest(){
        var search = new SearchLocalReq();
        search.setQuery("아이유");

        var result = naverClient.searchUrl(search);
        System.out.println(result);

    }

    @Test
    public void searchImage(){
        var search =  new SearchImageReq();
        search.setQuery("Abbey Road");

        var result = naverClient.searchImage(search);
        System.out.println(result);
    }
}
