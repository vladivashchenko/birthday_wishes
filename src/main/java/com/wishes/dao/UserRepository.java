package com.wishes.dao;

import com.wishes.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    List<User> findByName(String name);
    List<User> findAllByEmail(String email);
}

