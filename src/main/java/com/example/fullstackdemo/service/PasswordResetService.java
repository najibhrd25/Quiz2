package com.example.fullstackdemo.service;

import com.example.fullstackdemo.model.PasswordResetToken;
import com.example.fullstackdemo.repository.PasswordResetTokenRepository;
import com.example.fullstackdemo.model.User;
import com.example.fullstackdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Generate token dan simpan
    public PasswordResetToken createToken(String email) {

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return null; // email tidak ditemukan
        }

        // Hapus token lama (repository harus punya deleteByUser)
        tokenRepository.deleteByUser(user);

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken(
                token,
                user,
                LocalDateTime.now().plusMinutes(30) // token valid 30 menit
        );

        return tokenRepository.save(resetToken);
    }

    // Reset password
    public boolean resetPassword(String token, String newPassword) {

        PasswordResetToken prt = tokenRepository.findByToken(token);

        if (prt == null || prt.isExpired()) {
            return false;
        }

        User user = prt.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        tokenRepository.delete(prt);

        return true;
    }
}
