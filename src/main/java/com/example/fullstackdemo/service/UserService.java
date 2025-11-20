package com.example.fullstackdemo.service;

import com.example.fullstackdemo.model.User;
import com.example.fullstackdemo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register user baru
    public User registerUser(String name, String email, String password) {

        if (userRepository.existsByEmail(email)) {
            return null; // email sudah ada
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);

        // hash password
        user.setPassword(passwordEncoder.encode(password));

        // aktifkan akun (sementara, karena belum pakai email verification)
        user.setEnabled(true);

        return userRepository.save(user);
    }

    // Cari user berdasar email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
