package com.restuarant.restuarant.db;

import java.util.List;
import java.util.Optional;

/*
*  해당 Interface는 T타입, 즉 상속객체가 같은 것만 사용할 수 있다.
*  예) 과일 타입 <T = 과일)
*     class 포도 extend 과일
* */
public interface MemoryDBRepositoryIfs <T>{
    Optional<T> findById(int index);
    T save(T entity);
    void deleteById(int index);
    List<T> listAll();
    void deleteAll();
}
