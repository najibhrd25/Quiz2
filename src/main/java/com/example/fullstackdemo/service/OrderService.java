package com.example.fullstackdemo.service;

import com.example.fullstackdemo.model.Cart;
import com.example.fullstackdemo.model.Order;
import com.example.fullstackdemo.model.OrderItem;
import com.example.fullstackdemo.repository.CartRepository;
import com.example.fullstackdemo.repository.OrderItemRepository;
import com.example.fullstackdemo.repository.OrderRepository;
import com.example.fullstackdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderItemRepository itemRepo;

    @Autowired
    private CartRepository cartRepo;

    public void checkout(User user) {

        List<Cart> carts = cartRepo.findByUser(user);

        if (carts.isEmpty()) return;

        int totalHarga = carts.stream()
                .mapToInt(c -> c.getHarga() * c.getJumlah())
                .sum();

        // 1. Buat order
        Order order = new Order(user, totalHarga);
        orderRepo.save(order);

        // 2. Buat order items
        for (Cart c : carts) {
            OrderItem item = new OrderItem(
                    c.getNamaHt(),
                    c.getHarga(),
                    c.getJumlah(),
                    order
            );
            itemRepo.save(item);
        }

        // 3. Hapus cart
        cartRepo.deleteAll(carts);
    }
}
