package com.example.restaurant.dao;

import com.example.restaurant.model.OrderList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderListDao extends JpaRepository<OrderList,Integer> {
    Page<OrderList> findByCustomerId(int id, Pageable pageable);
    Page<OrderList> findByStatus(String status,Pageable pageable);
    OrderList findByDishesId(int id);
    OrderList findByCustomerIdAndDishesId(int customerId,int dishesId);
}
