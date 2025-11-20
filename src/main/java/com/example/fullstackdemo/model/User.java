package com.example.fullstackdemo.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private boolean emailVerified = false;

    // Constructor custom untuk register
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.emailVerified = false;
    }
    public void setEnabled(boolean enabled) {
    this.emailVerified = enabled;
    }


    // --- USERDETAILS ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();  // tidak ada role
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    // security flags
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }

    // user aktif hanya jika email sudah diverifikasi
    @Override
    public boolean isEnabled() {
        return emailVerified;
    }
}
