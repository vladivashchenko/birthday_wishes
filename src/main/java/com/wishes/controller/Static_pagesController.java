package com.wishes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by VladIvashchenko on 02.05.2017.
 */
@Controller
public class Static_pagesController {
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
        return "static_pages/about";
    }
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact() {
        return "static_pages/contact";
    }


}
