package com.example.fullstackdemo.repository;

import com.example.fullstackdemo.model.Cart;
import com.example.fullstackdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUser(User user);

    Cart findByUserAndNamaHt(User user, String namaHt);
}
