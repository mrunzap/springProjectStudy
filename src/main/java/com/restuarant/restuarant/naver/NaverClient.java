package com.restuarant.restuarant.naver;

import com.restuarant.restuarant.naver.dto.SearchImageReq;
import com.restuarant.restuarant.naver.dto.SearchImageRes;
import com.restuarant.restuarant.naver.dto.SearchLocalReq;
import com.restuarant.restuarant.naver.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NaverClient {

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverImageUrl;

    public SearchLocalRes searchUrl(SearchLocalReq searchLocalReq){
        // 쉽게 말해서 그냥 url 만드는 것이구나.
        var url = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                                                    .queryParams(searchLocalReq.toMultiValueMap()).build().encode().toUri();
        var header = new HttpHeaders();
        header.add("X-Naver-Client-Id", naverClientId);
        header.add("X-Naver-Client-Secret", naverSecret);

        header.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(header);

        var responseType = new ParameterizedTypeReference<SearchLocalRes>(){};

        var responseEntity = new RestTemplate().exchange(
                url
                , HttpMethod.GET
                , httpEntity
                , responseType
        );

        return responseEntity.getBody();


    }

    public SearchImageRes searchImage(SearchImageReq searchImageReq){
        var url = UriComponentsBuilder.fromUriString(naverImageUrl)
                .queryParams(searchImageReq.toMultiValueMap()).build().encode().toUri();
        var header = new HttpHeaders();
        header.add("X-Naver-Client-Id", naverClientId);
        header.add("X-Naver-Client-Secret", naverSecret);

        header.setContentType(MediaType.IMAGE_JPEG);

        var httpEntity = new HttpEntity<>(header);

        var responseType = new ParameterizedTypeReference<SearchImageRes>(){};

        var responseEntity = new RestTemplate().exchange(
                  url
                , HttpMethod.GET
                , httpEntity
                , responseType
        );

        return responseEntity.getBody();

    }
}
