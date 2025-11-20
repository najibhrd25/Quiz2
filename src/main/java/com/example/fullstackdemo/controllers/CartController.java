package com.example.fullstackdemo.controllers;

import com.example.fullstackdemo.model.User;
import com.example.fullstackdemo.security.UserDetailsImpl;
import com.example.fullstackdemo.service.CartService;
import com.example.fullstackdemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    // === HALAMAN KERANJANG ===
    @GetMapping
    public String index(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {

        if (userDetails == null) {
        return "redirect:/login";
        }

        User user = userDetails.getUser();

        var carts = cartService.getUserCart(user);

        int grandTotal = carts.stream()
            .mapToInt(c -> c.getHarga() * c.getJumlah())
            .sum();

        model.addAttribute("carts", carts);   // FIX → harus "carts"
        model.addAttribute("grandTotal", grandTotal);

        return "cart"; // templates/cart.html
}


    // === TAMBAH BARANG ===
    @PostMapping("/add")
    public String addToCart(
            @RequestParam String nama_ht,
            @RequestParam int harga,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        User user = userDetails.getUser();

        cartService.addToCart(nama_ht, harga, user);

        return "redirect:/cart";
    }

    // === UPDATE JUMLAH ===
    @PostMapping("/update/{id}")
    public String updateCart(
            @PathVariable Long id,
            @RequestParam int jumlah
    ) {
        cartService.updateJumlah(id, jumlah);
        return "redirect:/cart";
    }

    // === HAPUS ITEM ===
    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        cartService.deleteItem(id);
        return "redirect:/cart";
    }

    // === CHECKOUT ===
    @PostMapping("/checkout")
    public String checkout(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        User user = userDetails.getUser();

        orderService.checkout(user);

        return "checkout-success"; 
    }
}
