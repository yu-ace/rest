package com.example.restaurant.service;

import com.example.restaurant.model.Order;
import com.example.restaurant.model.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {

    Order createOrder(int customerId, List<OrderItem> orderItems,int tableId);
    Order addOrder(int customerId, List<OrderItem> orderItems);
    Order reduceOrder(int customerId, List<OrderItem> orderItems);
    Page<Order> getOrderList(Pageable pageable);
    Order getOrderByTableId(int tableId);
    Order payOrder(int tableId) throws Exception;
    Order getOrderByCustomerId(int customerId);
}
