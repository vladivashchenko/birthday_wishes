package com.wishes.services;

import com.wishes.dao.WishRepository;
import com.wishes.entities.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("wishService")
@Transactional
public class WishServiceImpl implements WishService {

    @Autowired
    private WishRepository wishRepository;

    @Override
    public Wish findById(int id) {
        return wishRepository.findOne(id);
    }

    @Override
    public  List<Wish> findWishesByUserId(int id) {
        return wishRepository.findWishesByUserId(id);
    }

    @Override
    public void saveWish(Wish wish) {
        wishRepository.save(wish);
    }

    @Override
    public void updateWish(Wish wish) {
        Wish entity = findById(wish.getId());
        if(entity != null) {
            entity.setWishes(wish.getWishes());
            entity.setPriority(wish.getPriority());
            entity.setLink(wish.getLink());
        }
    }

    @Override
    public void deleteWishById(int id){
        wishRepository.delete(id);
    }

    @Override
    public void deleteAllWishes(List<Wish> wishes){
        wishRepository.delete(wishes);
    }
}