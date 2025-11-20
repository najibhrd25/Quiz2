package com.example.fullstackdemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "carts")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaHt;
    private int harga;
    private int jumlah;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Cart() {}

    public Cart(String namaHt, int harga, int jumlah, User user) {
        this.namaHt = namaHt;
        this.harga = harga;
        this.jumlah = jumlah;
        this.user = user;
    }
}
