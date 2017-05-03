package com.wishes.controller;

import com.wishes.entity.User;
import com.wishes.entity.Wish;
import com.wishes.jsoup.JsoupParser;
import com.wishes.service.UserService;
import com.wishes.service.WishService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stazhor on 26.04.17.
 */
@Controller
public class WishController {
    @Autowired
    private WishService wishService;
    @Autowired
    private UserService userService;
    @RequestMapping("/wishes")
    public String list(Model model) {
        List<Wish> wishes = (List<Wish>) wishService.findAllWishes();
        model.addAttribute("wishes", wishes);
        return "wishes/index";
    }

    @RequestMapping("/wishes-{user_id}")
    public String list(Model model,@PathVariable("user_id") int user_id) throws IOException {
        List<Wish> wishes = wishService.findByUserId(user_id);
        List<String> links= new ArrayList<>();
        String link=null;
        for (int i = 0; i <wishes.size() ; i++){
            String wishurl= wishes.get(i).getLink();
            link=JsoupParser.parsePageHeaderInfo(wishurl);
            links.add(i, link);}

        User user = userService.findById(user_id);
        model.addAttribute("links",links);
        model.addAttribute("currentUser",user);
        model.addAttribute("wishes", wishes);
        return "wishes/userwishes";
    }
    @RequestMapping("/list-{user_id}")
    public String listForFriends(Model model,@PathVariable("user_id") int user_id) {
        List<Wish> wishes = (List<Wish>) wishService.findByUserId(user_id);
        User user = userService.findById(user_id);
        model.addAttribute("currentUser",user);
        model.addAttribute("wishes", wishes);
        return "wishes/wishes";
    }

     @RequestMapping("/wish-{user_id}")
    public String index(@PathVariable("user_id") int id, Model model) {
        Wish wish=wishService.findById(id);
        model.addAttribute("wish", wish);
        return "wishes/show";
    }

    @RequestMapping("/addwish-{user_id}")
    public String addWish(Model model,Wish wish,@PathVariable("user_id") int user_id) {
        User user = userService.findById(user_id);
        model.addAttribute("user",user);
        model.addAttribute("wish",new Wish());
        model.addAttribute("user_id",user_id);
        return "wishes/addwish";
    }
    @RequestMapping(value = { "/update-wish-{id}" }, method = RequestMethod.GET)
    public String editWish(@PathVariable("id") int id, Model model) {
        Wish wish = wishService.findById(id);
        model.addAttribute("wish", wish);
        model.addAttribute("edit", true);
        return "wishes/update";
    }
    @RequestMapping(value = { "/update-wish-{id}" }, method = RequestMethod.POST)
    public String updateWish(@Valid Wish wish, Model model, @PathVariable("id")  int id) {
        wishService.updateWish(wish);
        model.addAttribute("success", "Wish " + wish.getWishes() + " updated successfully");
        return "wishes/success";
    }
    @RequestMapping(value = "savewish-{user_id}", method = RequestMethod.POST)
    public String save(@PathVariable("user_id") int user_id,Wish wish,Model model){
        wishService.saveWish(wish);
        User user= userService.findById(user_id);

        model.addAttribute("user",user);
        model.addAttribute("success", "Wish: " + wish.getWishes() + " created successfully");
        return "wishes/success";
    }
    @RequestMapping(value = "/deletewish-{id}", method = RequestMethod.GET)
    public String deleteWish(@PathVariable("id") int wishId, Model model) {
        wishService.deleteWishById(wishId);
        return "redirect:wishes";
    }
}