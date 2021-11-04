package com.example.springblogmain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String diceRollHome(){
        return "views-lec/roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String diceRoll(@PathVariable int n , Model model){
        int diceRoll = (int) ((Math.random()*6)+1);
        model.addAttribute("diceRoll",diceRoll);
        boolean Congratulation = false;
        if(n==diceRoll){
            Congratulation = true;
        }
        model.addAttribute("Congratulation",Congratulation);
            return "views-lec/roll-dice";
        }
    }


