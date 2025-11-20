package com.example.fullstackdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HtController {

    @GetMapping("/ht")
    public String daftarHT(Model model) {

        List<Map<String, Object>> hts = List.of(
            Map.of("nama", "Model WLN", "harga", 10000, "gambar", "h1.jpeg"),
            Map.of("nama", "Comtect Analog", "harga", 5000, "gambar", "h2.jpeg"),
            Map.of("nama", "Comtect Digital", "harga", 8000, "gambar", "h3.jpeg")
        );

        model.addAttribute("hts", hts);
        return "ht";
    }
}
