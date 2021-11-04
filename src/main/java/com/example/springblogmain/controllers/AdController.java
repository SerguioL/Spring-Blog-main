package com.example.springblogmain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdController {

    @GetMapping("/ads")
    @ResponseBody
    public String showAds(){
        return "showing ad";
    }

    @GetMapping("/ads/{id}")
    @ResponseBody
    public String showSingleAd(@PathVariable long id){
        return "Showing ad: " + id;
    }

    @GetMapping("/bg/{color1}/font/{color2}")
    @ResponseBody
    public String returnBgFontColor(@PathVariable String color1, @PathVariable String color2) {
        return String.format("<h1 style='background-color:%s;color:%s'>THIS IS THE FONT</h1>", color1, color2);
    }

    @GetMapping("/name")
    @ResponseBody
    public String returnBgFontColor(@RequestParam String n) {
        return "Returning name: " + n;
    }

}
