package com.example.springblogmain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstController {

    @GetMapping("/hello")
    @ResponseBody
    public String returnHello(){
        return "Hello";
    }

    @GetMapping("/howdy")
    @ResponseBody
    public String returnHowdy(){
        return "Howdy";
    }

}
