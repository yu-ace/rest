package com.example.restaurant.service.impl;

import com.example.restaurant.dao.IOrderDao;
import com.example.restaurant.dao.IOrderItemDao;
import com.example.restaurant.model.Order;
import com.example.restaurant.model.OrderItem;
import com.example.restaurant.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    IOrderDao orderDao;
    @Autowired
    IOrderItemDao orderItemDao;


    @Override
    public Order createOrder(int customerId, List<OrderItem> orderItems,int tableId) {
        Order order = new Order();
        order.setCustomerId(customerId);
        double sum = 0;
        for(OrderItem orderList:orderItems){
            sum = sum + orderList.getTotal();
        }
        order.setTotal(sum);
        order.setTableId(tableId);
        order.setStatus("已下单");
        orderDao.save(order);
        return order;
    }

    @Override
    public Order addOrder(int customerId, List<OrderItem> orderItems) {
        Order order = orderDao.findByCustomerId(customerId);
        Double sum = order.getTotal();
        for(OrderItem orderItem : orderItems){
            sum = sum + orderItem.getTotal();
        }
        order.setTotal(sum);
        orderDao.save(order);
        return order;
    }

    @Override
    public Order reduceOrder(int customerId, List<OrderItem> orderItems) {
        Order order = orderDao.findByCustomerId(customerId);
        Double sum = order.getTotal();
        for(OrderItem orderItem : orderItems){
            sum = sum - orderItem.getTotal();
        }
        order.setTotal(sum);
        orderDao.save(order);
        return order;
    }

    @Override
    public Page<Order> getOrderList(Pageable pageable) {
        return orderDao.findAll(pageable);
    }

    @Override
    public Order getOrderByTableId(int tableId){
        return orderDao.findByTableIdAndStatus(tableId,"已下单");
    }

    @Override
    public Order payOrder(int tableId) throws Exception{
        Order order = orderDao.findByTableIdAndStatus(tableId,"已下单");
        List<OrderItem> orderItemList = orderItemDao.findByCustomerId(order.getCustomerId());
        for(OrderItem orderItem : orderItemList){
            if(orderItem.getStatus().equals("已下单")){
                throw new Exception("该桌菜品还没有上全，无法结账");
            }
        }
        order.setStatus("已结账");
        orderDao.save(order);
        return order;
    }

    @Override
    public Order getOrderByCustomerId(int customerId) {
        return orderDao.findByCustomerId(customerId);
    }
}
