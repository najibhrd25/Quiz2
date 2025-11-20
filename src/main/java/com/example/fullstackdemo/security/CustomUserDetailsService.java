package com.example.fullstackdemo.security;

import com.example.fullstackdemo.model.User;
import com.example.fullstackdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User tidak ditemukan"));

        // Kembalikan wrapper UserDetailsImpl (agar controller yang meng-cast aman)
        return new UserDetailsImpl(user);
    }
}
