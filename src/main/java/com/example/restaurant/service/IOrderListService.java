package com.example.restaurant.service;

import com.example.restaurant.model.OrderList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderListService {
    OrderList newOrderList(int dishesId,int customerId,double unitPrice,int count,double price);
    public void finialOrderList(int dishesId,int cookId);
    Page<OrderList> getOrderListByCustomerId(int customerId, Pageable pageable);
    Page<OrderList> getOrderListByStatus(String status,Pageable pageable);
    OrderList getOrderListByDishesId(int dishesId);
    OrderList reduceOrderList(int dishesId,int count);
    OrderList changeOrderListStatusOk(int dishesId,int cookId);
    OrderList reduceOrderListByCustomer(int dishesId,int customerId,int count);
}
