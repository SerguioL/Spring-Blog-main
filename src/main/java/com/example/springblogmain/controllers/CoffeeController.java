package com.example.springblogmain.controllers;

import com.example.springblogmain.models.Coffee;
import com.example.springblogmain.repositories.CoffeeRepository;
import com.example.springblogmain.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CoffeeController {

    private final CoffeeRepository coffeeRepository;
    private final EmailService emailService;

    public CoffeeController(CoffeeRepository coffeeRepository,EmailService emailService){
        this.coffeeRepository = coffeeRepository;
        this.emailService =emailService;
    }


//    @GetMapping("/coffee")
//    public String coffeeInfo(){
//        return "views-lec/coffee";
//    }

//    @GetMapping("/coffee/{roast}")
//    public String roastSelection(@PathVariable String roast, Model model){
//        model.addAttribute("roast", roast);
//        boolean choseDark = false;
//        if(roast.equals("dark")){
//            choseDark = true;
//        }
//        model.addAttribute("choseDark",choseDark);
//        return "view-lec/coffee";
//    }

//    @GetMapping("/coffee/{roast}")
//    public String roastSelection(@PathVariable String roast, Model model){
//        Coffee selection = new Coffee();
//        selection.setRoast(roast);
//        if(roast.equals("dark")){
//            selection.setOrigin("Colombia");
//        } else if(roast.equals("medium")){
//            selection.setOrigin("New Guinea");
//        } else {
//            selection.setOrigin("Kenya");
//        }
//        model.addAttribute("selection", selection);
//        return "view-lec/coffee";
//    }

    @GetMapping("/coffee")
    public String coffeeInfo(){
        return "coffees/coffee";
    }

    @GetMapping("/coffee/{roast}")
    public String roastSelection(@PathVariable String roast, Model model){
        model.addAttribute("selections", coffeeRepository.findByRoast(roast));
        return "coffees/coffee";
    }

    @GetMapping("/coffee/create")
    public String showCreateCoffeeForm(Model model){
        model.addAttribute("coffee", new Coffee());
        return "/coffees/create";
    }

    @PostMapping("/coffee/create")
    public String createCoffee(@ModelAttribute Coffee coffee){
        coffeeRepository.save(coffee);
        return "redirect:/coffees/coffee";
    }

    @PostMapping("/coffee")
    public String newsletterSignup(@RequestParam(name="email") String email, Model model){
        model.addAttribute("email", email);
        emailService.prepareAndSend(email, "You have signed up for coffee emails! Thank you!");
        return "coffees/coffee";
    }

}

