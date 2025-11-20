package com.example.fullstackdemo.repository;

import com.example.fullstackdemo.model.PasswordResetToken;
import com.example.fullstackdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    void deleteByUser(User user);
}
