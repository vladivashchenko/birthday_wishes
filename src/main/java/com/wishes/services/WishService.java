package com.wishes.services;

import com.wishes.entities.Wish;

import java.util.List;

/**
 * Created by stazhor on 10.05.17.
 */
public interface WishService {
    Wish findById(int id);

    List<Wish> findWishesByUserId(int id);

    void saveWish(Wish wish);

    void updateWish(Wish wish);

    void deleteWishById(int id);

    void deleteAllWishes(List<Wish> wishes);
}
