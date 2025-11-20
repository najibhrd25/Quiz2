package com.example.fullstackdemo.service;

import com.example.fullstackdemo.model.User;
import com.example.fullstackdemo.model.VerificationToken;
import com.example.fullstackdemo.repository.UserRepository;
import com.example.fullstackdemo.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class VerificationService {

    @Autowired
    private VerificationTokenRepository tokenRepo;

    @Autowired
    private UserRepository userRepo;

    // === Membuat token verifikasi ===
    public VerificationToken createToken(User user) {

        String token = UUID.randomUUID().toString();

        VerificationToken vt = new VerificationToken();
        vt.setToken(token);
        vt.setUser(user);
        vt.setExpiredAt(LocalDateTime.now().plusHours(24));

        return tokenRepo.save(vt);
    }

    // === Verifikasi token ===
    public boolean verify(String token) {

        VerificationToken vt = tokenRepo.findByToken(token);

        if (vt == null || vt.isExpired()) {
            return false;
        }

        User user = vt.getUser();
        user.setEmailVerified(true);
        userRepo.save(user);

        // token sudah tidak diperlukan setelah verifikasi
        tokenRepo.delete(vt);

        return true;
    }
}
