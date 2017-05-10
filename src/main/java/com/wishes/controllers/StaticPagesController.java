package com.wishes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class StaticPagesController {
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
        return "static_pages/about";
    }
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact() {
        return "static_pages/contact";
    }


}
