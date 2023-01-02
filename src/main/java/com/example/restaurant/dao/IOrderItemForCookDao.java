package com.example.restaurant.dao;

import com.example.restaurant.model.OrderItemForCook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderItemForCookDao extends JpaRepository<OrderItemForCook,Integer> {
    Page<OrderItemForCook> findByCategoryNameAndStatus(String category, String status, Pageable pageable);
}
