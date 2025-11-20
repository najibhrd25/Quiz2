package com.example.fullstackdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.example.fullstackdemo.security.UserDetailsImpl;
import com.example.fullstackdemo.model.User;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if (userDetails != null) {
            User user = userDetails.getUser(); // ambil User
            model.addAttribute("user", user);  // kirim User ke template
        }

        return "home";
    }
}
