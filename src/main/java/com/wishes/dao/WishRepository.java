package com.wishes.dao;

import com.wishes.entity.Wish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by stazhor on 27.04.17.
 */
@Repository
public interface WishRepository extends CrudRepository<Wish,Integer> {
    List<Wish> findWishesByUserId(int id);
}
