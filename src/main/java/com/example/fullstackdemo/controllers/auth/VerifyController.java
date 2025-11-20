package com.example.fullstackdemo.controllers.auth;

import com.example.fullstackdemo.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VerifyController {

    @Autowired
    private VerificationService verifyService;

    @GetMapping("/verify/{token}")
    public String verify(@PathVariable String token, Model model) {

        if (!verifyService.verify(token)) {
            model.addAttribute("status", "Link verifikasi tidak valid.");
        } else {
            model.addAttribute("status", "Email berhasil diverifikasi. Silahkan login.");
        }

        return "auth/verify";
    }
}
