package com.example.restaurant.dao;

import com.example.restaurant.model.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderItemDao extends JpaRepository<OrderItem,Integer> {
    Page<OrderItem> findByCustomerId(int id, Pageable pageable);
    Page<OrderItem> findByStatus(String status, Pageable pageable);
    OrderItem findByDishesId(int id);
    OrderItem findById(int id);
    OrderItem findByCustomerIdAndDishesId(int customerId, int dishesId);
    List<OrderItem> findByCustomerId(int customerId);
    Page<OrderItem> findByCustomerIdAndHistoryOrderItem(int customerId,int historyOrderItem,Pageable pageable);
}
