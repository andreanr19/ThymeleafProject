package co.edu.icesi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class MainController {

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "/logout";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "/access-denied";
    }
}
