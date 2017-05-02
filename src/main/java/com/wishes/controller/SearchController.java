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

@Controller
public class SearchController {

    @Autowired
    UserService userService;
    @RequestMapping(value = "/search")
    public String Search(@RequestParam(value = "email") String email, Model model) {
        if(email != null){
            userService.findByEmail(email);
            User user = userService.findByEmail(email);
            model.addAttribute("email", email);
            model.addAttribute("user", user);
        }
        return "user/show";
    }
    @RequestMapping(value = "/find")
    public String find(@RequestParam(value = "name") String name, Model model) {
        if(name != null){
            userService.findByName(name);
            User user = userService.findByName(name);
            model.addAttribute("name", name);
            model.addAttribute("user", user);
        }
        return "user/showForFriend";
    }


}