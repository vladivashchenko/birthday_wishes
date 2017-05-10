package com.wishes.services;

import com.wishes.entities.User;

import java.util.List;

/**
 * Created by stazhor on 10.05.17.
 */
public interface UserService {
    User findById(int id);

    User findByEmail(String email);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(int id);

    List<User> findAllUsers();

    List<User> findByName(String name);

    List<User> findAllByEmail(String email);
}
