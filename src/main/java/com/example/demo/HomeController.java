package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

//    @RequestMapping("/secure")
//    public String secure() {
//        return "secure";
//    }

    @GetMapping
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){

        model.addAttribute("user", user);

        if(result.hasErrors()){
            return "register";
        }else{
            userService.saveUser(user);
            System.out.println("User account created");
        }

        return "index";
    }

}
