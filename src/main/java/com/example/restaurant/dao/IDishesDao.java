package com.example.restaurant.dao;

import com.example.restaurant.model.Dishes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDishesDao extends JpaRepository<Dishes,Integer> {
    Dishes findById(int id);
    Dishes findByName(String name);
    Page<Dishes> findByCategoryId(int id, Pageable pageable);
}
