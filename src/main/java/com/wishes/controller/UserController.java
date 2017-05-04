package com.wishes.controller;

import com.wishes.entity.User;
import com.wishes.service.UserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 * Created by stazhor on 26.04.17.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/")
    public String index(Model model) {

        return "user/index";
    }
    @RequestMapping("/users")
    public String users(Model model) {
        List<User> users = (List<User>) userService.findAllUsers();
        model.addAttribute("users", users);
        return "user/users";
    }
    @RequestMapping("/user-{id}")
    public String show(@PathVariable("id") int id, Model model) {
        User user=userService.findById(id);
        model.addAttribute("user", user);
        return "user/show";
    }
    @RequestMapping("/userprofile-{id}")
    public String showForFriend(@PathVariable("id") int id, Model model) {
        User user=userService.findById(id);
        model.addAttribute("user", user);
        return "user/showForFriend";
    }

    @RequestMapping("/registration")
    public String addUser(Model model){
        model.addAttribute("user",new User());
    return "user/registration";
    }
    @RequestMapping(value = { "/update-user-{id}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable("id") int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "user/update";
    }
    @RequestMapping(value = { "/update-user-{id}" }, method = RequestMethod.POST)
    public String updateUser(User user, Model model, @PathVariable("id")  int id) {
        userService.updateUser(user);
        model.addAttribute("message", "User " + user.getName() + " "+ user.getEmail() + " updated successfully");
        return "user/success";
    }
    @RequestMapping(value = "save", method = RequestMethod.GET)
    public String saveUser(){
        return "redirect:/users";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@Valid User user, BindingResult bindingResult,Model model) {
        List<User> users = userService.findAllByEmail(user.getEmail());
        if (bindingResult.hasErrors()||users.size()>0) {
            model.addAttribute("message", "User with email "+user.getEmail()+" already exists");
            return "/user/registration";
        }
        if(user.getEmail().toString()==null){
            model.addAttribute("message", "Please fill form");
        }
        if(users.size()==0){
            userService.saveUser(user);
            model.addAttribute("message", "User " + user.getName() + " "+ user.getEmail() + " created successfully");
        }
        return "user/success";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") int userId, Model model) {
        userService.deleteUserById(userId);
        return "redirect:/users";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }
}