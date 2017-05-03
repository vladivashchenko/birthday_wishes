package com.wishes.service;

/**
 * Created by stazhor on 26.04.17.
 */

import com.wishes.dao.WishRepository;
import com.wishes.entity.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("wishService")
@Transactional
public class WishService {

    @Autowired
    private WishRepository wishRepository;

    public Wish findById(int id) {
        return wishRepository.findOne(id);
    }

    public  List<Wish>  findByUserId(int id) {
        return wishRepository.findByUserId(id);
    }

    public  Wish  findWishByUserId(int id) {
        return wishRepository.findWishByUserId(id);
    }

    public void saveWish(Wish wish) {
        wishRepository.save(wish);
    }

    public void updateWish(Wish wish){
        Wish entity = findById(wish.getId());
        if(entity!=null){
            entity.setWishes(wish.getWishes());
        }
    }

    public void deleteWishById(int id){
        wishRepository.delete(id);
    }

    public void deleteAllWishes(){
        wishRepository.deleteAll();
    }

    public List<Wish> findAllWishes(){
        return (List<Wish>) wishRepository.findAll();
    }
}