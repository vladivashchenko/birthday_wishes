package com.wishes.dao;

import com.wishes.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by stazhor on 26.04.17.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
        List<User> findByEmail(String email);
    }

