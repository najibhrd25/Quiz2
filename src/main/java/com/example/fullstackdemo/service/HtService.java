package com.example.fullstackdemo.service;

import com.example.fullstackdemo.model.Ht;
import com.example.fullstackdemo.repository.HtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HtService {

    @Autowired
    private HtRepository repo;

    public List<Ht> getAll() {
        return repo.findAll();
    }
}
