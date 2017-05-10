package com.wishes.dao;

import com.wishes.entities.Wish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WishRepository extends CrudRepository<Wish,Integer> {
    List<Wish> findWishesByUserId(int id);
}
