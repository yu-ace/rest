package com.example.restaurant.service;

import com.example.restaurant.model.OrderItemForCook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderItemForCookService {
    Page<OrderItemForCook> getOrderListBookByCategoryAndStatus(String category, String status, Pageable pageable);
}
