package com.example.restaurant.service;

import com.example.restaurant.model.OrderListCook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderListCookService {
    Page<OrderListCook> getOrderListBookByCategoryAndStatus(String category, String status, Pageable pageable);
}
