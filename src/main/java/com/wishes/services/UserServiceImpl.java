package com.wishes.services;

import java.util.List;
import com.wishes.dao.UserRepository;
import com.wishes.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
       userRepository.save(user);
    }

    @Override
    public void updateUser(User user){
        User entity = findById(user.getId());
        if(entity!=null){
            entity.setName(user.getName());
            entity.setEmail(user.getEmail());
        }
    }

    @Override
    public void deleteUserById(int id){
        userRepository.delete(id);
    }

    @Override
    public List<User> findAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public  List<User> findAllByEmail(String email){
        return userRepository.findAllByEmail(email);
    }

    @Override
    public boolean isUserExists(User user) {
        List<User> users = userRepository.findAllByEmail(user.getEmail());
        if(users.size()>0)
            return true;
        else
            return false;
    }


}