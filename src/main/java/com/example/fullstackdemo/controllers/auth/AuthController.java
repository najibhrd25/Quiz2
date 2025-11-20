package com.example.fullstackdemo.controllers.auth;

import com.example.fullstackdemo.model.User;
import com.example.fullstackdemo.repository.UserRepository;
import com.example.fullstackdemo.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private VerificationService verifyService;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            Model model
    ) {

        if (userRepo.findByEmail(email).isPresent()) {
            model.addAttribute("error", "Email sudah digunakan.");
            return "auth/register";
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encoder.encode(password));

        // sementara TRUE dulu untuk dev
        user.setEmailVerified(true);

        userRepo.save(user);

        var token = verifyService.createToken(user);

        model.addAttribute("verifyLink",
                "http://localhost:8080/verify/" + token.getToken());

        return "auth/register-success";
    }
}
