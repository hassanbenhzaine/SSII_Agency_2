package com.youcode.gestionemployes.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
public class HomeController{
    @RequestMapping("/")
    public String index() {
        return "redirect:/employe/manage";
    }
}
