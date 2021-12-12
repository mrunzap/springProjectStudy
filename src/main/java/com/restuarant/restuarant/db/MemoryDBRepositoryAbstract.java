package com.restuarant.restuarant.db;

import com.restuarant.restuarant.wishlist.dto.WishListDto;
import com.restuarant.restuarant.wishlist.entity.WishListEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class MemoryDBRepositoryAbstract<T extends MemoryDBEntity> implements MemoryDBRepositoryIfs<T>{

    //final 사용하는 것 잊어버림
    /*
    * 원시타입 : 상수 값 변경 불가능
    * 객체타입 : 객체 속성 변경 가능하나, 객체 자체를 할당할 수 없음
    * 메서드인자 : 메서드 내에서만 변경가능능    * */
    private final List<T> db = new ArrayList<T>();
    private int index = 0;

    @Override
    public Optional<T> findById(int index) {
        return db.stream().filter(it -> it.getIndex() == index).findFirst();
    }


    @Override
    public T save(T entity) {
        var optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();
        if (optionalEntity.isEmpty()){
            index++;
            entity.setIndex(index);
            db.add(entity);
            return entity;
        }else{
            var preIndex = optionalEntity.get().getIndex();
            entity.setIndex(preIndex);
            deleteById(preIndex);
            db.add(entity);
            return entity;

        }
    }

    @Override
    public void deleteById(int index) {
        var optionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst();
        if (optionalEntity.isPresent()){
            db.remove(optionalEntity.get());
        }

    }

    @Override
    public List<T> listAll() {
        return db;
    }

    @Override
    public void deleteAll() {
        db.removeAll(db);
    }
}
