package com.example.restaurant.dao;

import com.example.restaurant.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillDao extends JpaRepository<Bill,Integer> {
    Bill findByCustomerId(int id);
}
