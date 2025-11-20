package com.example.fullstackdemo.service;

import com.example.fullstackdemo.model.Cart;
import com.example.fullstackdemo.model.User;
import com.example.fullstackdemo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository repo;

    // Mendapatkan keranjang user
    public List<Cart> getUserCart(User user) {
        return repo.findByUser(user);
    }

    // Menambah barang ke keranjang
    public void addToCart(String namaHt, int harga, User user) {

        // Jika barang sama sudah ada, cukup tambah jumlah
        Cart existing = repo.findByUserAndNamaHt(user, namaHt);

        if (existing != null) {
            existing.setJumlah(existing.getJumlah() + 1);
            repo.save(existing);
            return;
        }

        Cart cart = new Cart();
        cart.setNamaHt(namaHt);
        cart.setHarga(harga);
        cart.setJumlah(1);
        cart.setUser(user);

        repo.save(cart);
    }

    // Update jumlah barang
    public void updateJumlah(Long id, int jumlah) {
        Cart cart = repo.findById(id).orElseThrow();
        cart.setJumlah(jumlah);
        repo.save(cart);
    }

    // Hapus item
    public void deleteItem(Long id) {
        repo.deleteById(id);
    }

    // Kosongkan keranjang
    public void clearCart(User user) {
        List<Cart> list = repo.findByUser(user);
        repo.deleteAll(list);
    }
}
