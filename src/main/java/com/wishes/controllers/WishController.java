package com.wishes.controllers;

import com.wishes.entities.User;
import com.wishes.entities.Wish;
import com.wishes.jsoup.JsoupParser;
import com.wishes.services.UserService;
import com.wishes.services.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WishController {
    @Autowired
    private WishService wishService;

    @Autowired
    private UserService userService;

    @RequestMapping("/wishes-{user_id}")
    public String list(Model model,@PathVariable("user_id") int user_id) throws IOException {
        List<Wish> wishes = wishService.findWishesByUserId(user_id);
        List<String> links= new ArrayList<>();
        String link=null;
        /*parse link*/
        for (int i = 0; i <wishes.size() ; i++){
            String wishurl= wishes.get(i).getLink();
            link=JsoupParser.parsePageHeaderInfo(wishurl);
            links.add(i, link);
        }
        User user = userService.findById(user_id);

        model.addAttribute("links",links);
        model.addAttribute("user",user);
        model.addAttribute("wishes", wishes);

        return "wishes/userwishes";
    }

    @RequestMapping("/list-{user_id}")
    public String listForFriends(Model model,@PathVariable("user_id") int user_id) throws IOException {
        List<Wish> wishes = wishService.findWishesByUserId(user_id);
        List<String> links= new ArrayList<>();
        String link=null;
        /*parse link*/
        for (int i = 0; i <wishes.size() ; i++){
            String wishurl= wishes.get(i).getLink();
            link=JsoupParser.parsePageHeaderInfo(wishurl);
            links.add(i, link);}

        User user = userService.findById(user_id);

        model.addAttribute("links",links);
        model.addAttribute("user",user);
        model.addAttribute("wishes", wishes);

        return "wishes/wishes";
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
        User user=wish.getUser();
        model.addAttribute("wish", wish);
        model.addAttribute("user", user);
        return "wishes/update";
    }

    @RequestMapping(value = { "/update-wish-{id}" }, method = RequestMethod.POST)
    public String updateWish(Wish wish, Model model, @PathVariable("id")  int id) {
        wishService.updateWish(wish);
        wish = wishService.findById(id);
        User user=wish.getUser();

        model.addAttribute("wish", wish);
        model.addAttribute("user", user);
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

        Wish wish = wishService.findById(wishId);
        User user= wish.getUser();
        wishService.deleteWishById(wishId);
        model.addAttribute("user", user);
        model.addAttribute("success","Wish was deleted successfully" );

        return "wishes/success";
    }
    @RequestMapping(value = "deleteAll-{user_id}")
    public String deleteAllWishes(@PathVariable("user_id") int user_id,Model model) {
        List<Wish> wishes = wishService.findWishesByUserId(user_id);
        for (Wish wish:wishes){
            User user = wish.getUser();
            model.addAttribute("user",user);
        }
        wishService.deleteAllWishes(wishes);
        model.addAttribute("success","Wishes was deleted successfully" );

        return "wishes/success";
    }
}