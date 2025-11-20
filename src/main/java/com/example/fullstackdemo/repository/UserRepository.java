package com.example.fullstackdemo.repository;

import com.example.fullstackdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Cari user berdasarkan email
    Optional<User> findByEmail(String email);

    // Cek apakah email sudah terdaftar (untuk register)
    boolean existsByEmail(String email);
}
