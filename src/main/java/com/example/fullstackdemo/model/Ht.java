package com.example.fullstackdemo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hts")
public class Ht {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    private int harga;
    private String gambar;

    public Ht() {}

    public Ht(String nama, int harga, String gambar) {
        this.nama = nama;
        this.harga = harga;
        this.gambar = gambar;
    }

    public Long getId() { return id; }
    public String getNama() { return nama; }
    public int getHarga() { return harga; }
    public String getGambar() { return gambar; }

    public void setNama(String nama) { this.nama = nama; }
    public void setHarga(int harga) { this.harga = harga; }
    public void setGambar(String gambar) { this.gambar = gambar; }
}
