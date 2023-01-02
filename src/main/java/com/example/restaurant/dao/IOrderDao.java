package com.example.restaurant.dao;

import com.example.restaurant.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDao extends JpaRepository<Order,Integer> {
    Order findByTableIdAndStatus(int id,String status);
    Order findByCustomerId(int customerId);
}
