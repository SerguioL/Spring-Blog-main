package com.example.springblogmain.controllers;

import com.example.springblogmain.models.Ad;
import com.example.springblogmain.models.User;
import com.example.springblogmain.repositories.AdRepository;
import com.example.springblogmain.repositories.UserRepository;
import com.example.springblogmain.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdController {

    private final AdRepository adRepository;

    private final EmailService emailService;

    private final UserRepository userRepository;

    public AdController(AdRepository adRepository, EmailService emailService, UserRepository userRepository){
        this.adRepository = adRepository;
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    @GetMapping("/ads")
    public String showAds(Model model){
        model.addAttribute("ads", adRepository.findAll());
        return "ads/index";
    }

    @GetMapping("/ads/create")
    public String showCreateAdsForm(Model model){
        model.addAttribute("ad", new Ad());
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public String createAdWithForm(@ModelAttribute Ad ad){
        User user = userRepository.getById(1L);
        ad.setOwner(user);
        adRepository.save(ad);
        emailService.prepareAndSend(ad,"You created " + ad.getTitle(), ad.getDescription());
        return "redirect:/ads";
    }

//    @GetMapping("/ads")
//    @ResponseBody
//    public String creatAd(@RequestBody Ad newAd){
//        adRepository.save(newAd);
//        return String.format("Ad created with an ID of %s", newAd.getId());
//    }

    @GetMapping("/ads/{title}")
    @ResponseBody
    public List<Ad> getByTitle(@PathVariable String title){
        return adRepository.findByTitle(title);
    }

//    @GetMapping("/ads/{titlePart}")
//    @ResponseBody
//    public List<Ad> getByTitle(@PathVariable String titlePart){
//        return adRepository.findByTitleLike(titlePart);
//    }

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
