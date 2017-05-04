package com.wishes.service;

/**
 * Created by stazhor on 26.04.17.
 */
import java.util.List;

import com.wishes.dao.UserRepository;
import com.wishes.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintValidatorContext;

@Service("userService")
@Transactional
public class UserService{

    @Autowired
    private UserRepository userRepository;

    public User findById(int id) {
        return userRepository.findOne(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user){
        User entity = findById(user.getId());
        if(entity!=null){
            entity.setName(user.getName());
            entity.setEmail(user.getEmail());
        }
    }

    public void deleteUserById(int id){
        userRepository.delete(id);
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    public List<User> findAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    public boolean isUserExist(User user) {
        return findByEmail(user.getEmail()) != null;
    }

    public List<User> findByName(String name) { return userRepository.findByName(name); }

    public  List<User> findAllByEmail(String email){ return userRepository.findAllByEmail(email);}
}