package com.example.restaurant.service.impl;

import com.example.restaurant.dao.IOrderListCookDao;
import com.example.restaurant.model.OrderListCook;
import com.example.restaurant.service.IOrderListCookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderListCookService implements IOrderListCookService {

    @Autowired
    IOrderListCookDao orderListCookDao;

    @Override
    public Page<OrderListCook> getOrderListBookByCategoryAndStatus(String category, String status, Pageable pageable) {
        return orderListCookDao.findByCategoryNameAndStatus(category, status, pageable);
    }
}
