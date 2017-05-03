package com.wishes.dao;

import com.wishes.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by stazhor on 27.04.17.
 */
@Repository
public interface WishRepository extends JpaRepository<Wish,Integer> {
    List<Wish> findByUserId(int id);
    Wish findWishByUserId(int id);
}
