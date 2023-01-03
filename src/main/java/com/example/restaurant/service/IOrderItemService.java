package com.example.restaurant.service;

import com.example.restaurant.model.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderItemService {
    OrderItem newOrderItem(int dishesId, int customerId, int count);
    public void finialOrderItem(int Id,int cookId);
    Page<OrderItem> getOrderItemByCustomerId(int customerId, Pageable pageable);
    Page<OrderItem> getOrderItemByStatus(String status, Pageable pageable);
    OrderItem getOrderItemByDishesIdAndCustomerId(int dishesId,int customerId) throws Exception;
    Page<OrderItem> getOrderItem(Pageable pageable);
    OrderItem reduceOrderItemByCustomer(int dishesId, int customerId, int count);
    List<OrderItem> getOrderItemByCustomerId(int customerId);
    Page<OrderItem> getOrderItemByCustomerIdAndHistoryOrderItem(int customerId,Pageable pageable);
}
