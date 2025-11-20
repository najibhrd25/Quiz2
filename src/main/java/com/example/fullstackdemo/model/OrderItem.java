package com.example.fullstackdemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaHt;
    private int harga;
    private int jumlah;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem() {}

    public OrderItem(String namaHt, int harga, int jumlah, Order order) {
        this.namaHt = namaHt;
        this.harga = harga;
        this.jumlah = jumlah;
        this.order = order;
    }
}
