package com.example.restaurant.service.impl;

import com.example.restaurant.dao.IOrderItemForCookDao;
import com.example.restaurant.model.OrderItemForCook;
import com.example.restaurant.service.IOrderItemForCookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderItemForCookService implements IOrderItemForCookService {

    @Autowired
    IOrderItemForCookDao orderListCookDao;

    @Override
    public Page<OrderItemForCook> getOrderListBookByCategoryAndStatus
            (String category, String status, Pageable pageable) {
        return orderListCookDao.findByCategoryNameAndStatus(category, status, pageable);
    }
}
