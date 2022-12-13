package com.example.restaurant.dao;

import com.example.restaurant.model.OrderListCook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderListCookDao extends JpaRepository<OrderListCook,Integer> {
    Page<OrderListCook> findByCategoryNameAndStatus(String category, String status, Pageable pageable);
}
