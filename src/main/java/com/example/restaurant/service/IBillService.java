package com.example.restaurant.service;

import com.example.restaurant.model.Bill;
import com.example.restaurant.model.OrderList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBillService {
    Bill newBill(int customerId,double total);
    Bill getBillListByCustomerId(int id);
    Bill createOrder(int customerId,List<OrderList> orderItems);
}
