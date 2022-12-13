package com.example.restaurant.service.impl;

import com.example.restaurant.dao.IOrderListDao;
import com.example.restaurant.model.OrderList;
import com.example.restaurant.service.IOrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderListService implements IOrderListService {

    @Autowired
    IOrderListDao orderListDao;

    @Override
    public OrderList newOrderList(int dishesId, int customerId, double unitPrice, int count, double price) {
        OrderList orderList = new OrderList();
        orderList.setDishesId(dishesId);
        orderList.setCustomerId(customerId);
        orderList.setUnitPrice(unitPrice);
        orderList.setCount(count);
        orderList.setPrice(price);
        orderList.setTime(new Date());
        orderList.setStatus("已下单");
        orderList.setCookId(-1);
        orderListDao.save(orderList);
        return orderList;
    }

    @Override
    public void finialOrderList(int dishesId, int cookId) {
        OrderList orderList = orderListDao.findByDishesId(dishesId);
        orderList.setStatus("已完成");
        orderList.setCookId(cookId);
        orderListDao.save(orderList);
    }

    @Override
    public Page<OrderList> getOrderListByCustomerId(int customerId, Pageable pageable) {
        return orderListDao.findByCustomerId(customerId,pageable);
    }

    @Override
    public Page<OrderList> getOrderListByStatus(String status, Pageable pageable) {
        return orderListDao.findByStatus(status,pageable);
    }

    @Override
    public OrderList getOrderListByDishesId(int dishesId) {
        return orderListDao.findByDishesId(dishesId);
    }

    @Override
    public OrderList reduceOrderList(int dishesId, int count) {
        OrderList orderList = orderListDao.findByDishesId(dishesId);
        orderList.setCount(orderList.getCount() - count);
        orderListDao.save(orderList);
        return orderList;
    }

    @Override
    public OrderList changeOrderListStatusOk(int dishesId, int cookId) {
        OrderList orderList = orderListDao.findByDishesId(dishesId);
        orderList.setStatus("已完成");
        orderList.setCookId(cookId);
        orderListDao.save(orderList);
        return orderList;
    }

    @Override
    public OrderList reduceOrderListByCustomer(int dishesId, int customerId, int count) {
        OrderList orderList = orderListDao.findByCustomerIdAndDishesId(customerId,dishesId);
        orderList.setCount(orderList.getCount() - count);
        orderListDao.save(orderList);
        return orderList;
    }
}
