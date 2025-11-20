package com.example.fullstackdemo.repository;

import com.example.fullstackdemo.model.Ht;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HtRepository extends JpaRepository<Ht, Long> {
}
