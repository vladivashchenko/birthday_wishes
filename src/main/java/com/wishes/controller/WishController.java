package com.wishes.controller;

import com.wishes.entity.Wish;
import com.wishes.service.WishService;
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
public class WishController {
    @Autowired
    private WishService wishService;
    @RequestMapping("/wishes")
    public String list(Model model) {
        List<Wish> wishes = (List<Wish>) wishService.findAllWishes();
        model.addAttribute("wishes", wishes);
        return "wishes/index";
    }

    @RequestMapping("/wishes-{user_id}")
    public String list(Model model,@PathVariable("user_id") int user_id) {
        List<Wish> wishes = (List<Wish>) wishService.findByUserId(user_id);
        model.addAttribute("wishes", wishes);
        return "wishes/userwishes";
    }

     @RequestMapping("/wish-{user_id}")
    public String index(@PathVariable("user_id") int id, Model model) {
        Wish wish=wishService.findById(id);
        model.addAttribute("wish", wish);
        return "wishes/show";
    }

    @RequestMapping("/addwish")
    public String addWish(Model model){
        model.addAttribute("wish",new Wish());
    return "wishes/addwish";
    }
    @RequestMapping(value = { "/update-wish-{id}" }, method = RequestMethod.GET)
    public String editWish(@PathVariable("id") int id, Model model) {
        Wish wish = wishService.findById(id);
        model.addAttribute("wish", wish);
        model.addAttribute("edit", true);
        return "updatewish";
    }
    @RequestMapping(value = { "/update-wish-{id}" }, method = RequestMethod.POST)
    public String updateWish(@Valid Wish wish, Model model, @PathVariable("id")  int id) {
        wishService.updateWish(wish);
        model.addAttribute("success", "Wish " + wish.getWishes() + " updated successfully");
        return "success";
    }
    @RequestMapping(value = "savewish", method = RequestMethod.POST)
    public String save(Wish wish){
        wishService.saveWish(wish);
        return "redirect:wishes";
    }
    @RequestMapping(value = "/deletewish-{id}", method = RequestMethod.GET)
    public String deleteWish(@PathVariable("id") int wishId, Model model) {
        wishService.deleteWishById(wishId);
        return "redirect:wishes";
    }
}