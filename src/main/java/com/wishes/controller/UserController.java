package com.wishes.controller;

import com.wishes.entity.User;
import com.wishes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        List<User> users = (List<User>) userService.findAllUsers();
        model.addAttribute("users", users);
        return "index";
    }
     @RequestMapping("/user-{id}")
    public String index(@PathVariable("id") int id, Model model) {
        User user=userService.findById(id);
        model.addAttribute("user", user);
        return "show";
    }

    @RequestMapping("/registration")
    public String addUser(Model model){
        model.addAttribute("user",new User());
    return "registration";
    }
    @RequestMapping(value = { "/update-user-{id}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable("id") int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "update";
    }
    @RequestMapping(value = { "/update-user-{id}" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, Model model, @PathVariable("id")  int id) {
        userService.updateUser(user);
        model.addAttribute("success", "User " + user.getName() + " "+ user.getEmail() + " updated successfully");
        return "registrationsuccess";
    }
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(User user){
        userService.saveUser(user);
        return "redirect:/";
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") int userId, Model model) {
        userService.deleteUserById(userId);
        return "redirect:/";
    }
}