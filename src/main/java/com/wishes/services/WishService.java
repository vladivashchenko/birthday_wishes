package com.wishes.services;

import com.wishes.entities.User;
import com.wishes.entities.Wish;

import java.io.IOException;
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

    List<String> parseLink(List<String> links,List<Wish> wishes) throws IOException;

    User findUserbyWishId(Wish wish, int id);

}
