package com.wishes.controller;

import com.wishes.entity.User;
import com.wishes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    UserService userService;
    @RequestMapping(value = "/search")
    public String Search(@RequestParam(value = "email") String email, Model model) {
        User user = userService.findByEmail(email);
        if (user==null){
            model.addAttribute("message", "Incorrect email");
            return "user/login";
        }
        else{
        model.addAttribute("email", email);
        model.addAttribute("user", user);}
        return "user/show";
    }
    @RequestMapping(value = "/find")
    public String find(@RequestParam(value = "name") String name, Model model) {
        List<User> users = userService.findByName(name);
        if(users!=null){
            model.addAttribute("users", users);
        }
        return "user/showForFriend";
    }
}