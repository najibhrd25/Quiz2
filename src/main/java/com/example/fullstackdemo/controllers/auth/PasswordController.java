package com.example.fullstackdemo.controllers.auth;

import com.example.fullstackdemo.model.PasswordResetToken;
import com.example.fullstackdemo.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private PasswordResetService passwordResetService;

    // ============================
    // REQUEST RESET PASSWORD
    // ============================
    @PostMapping("/email")
    public String sendResetLink(@RequestParam String email, Model model) {

        PasswordResetToken token = passwordResetService.createToken(email);

        if (token == null) {
            model.addAttribute("error", "Email tidak ditemukan.");
            return "password/email";
        }

        // Normally: kirim email berisi link ini
        String resetUrl = "http://localhost:8080/password/reset/" + token.getToken();
        model.addAttribute("success", "Link reset password: " + resetUrl);

        // UI kamu tetap jalan
        return "password/email";
    }

    // ============================
    // HALAMAN FORM RESET PASSWORD
    // ============================
    @GetMapping("/reset/{token}")
    public String resetPage(@PathVariable String token, Model model) {
        model.addAttribute("token", token);
        return "password/reset";
    }

    // ============================
    // SUBMIT PASSWORD BARU
    // ============================
    @PostMapping("/update")
    public String updatePassword(
            @RequestParam String token,
            @RequestParam String password,
            Model model
    ) {

        boolean status = passwordResetService.resetPassword(token, password);

        if (!status) {
            model.addAttribute("error", "Token tidak valid atau sudah kadaluarsa.");
            return "password/reset";
        }

        model.addAttribute("success", "Password berhasil diganti! Silakan login.");
        return "auth/login";
    }
}
