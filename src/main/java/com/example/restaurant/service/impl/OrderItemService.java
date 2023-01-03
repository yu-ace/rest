package com.example.restaurant.service.impl;

import com.example.restaurant.dao.IDishesDao;
import com.example.restaurant.dao.IOrderItemDao;
import com.example.restaurant.dao.IShoppingCartDao;
import com.example.restaurant.model.Dishes;
import com.example.restaurant.model.OrderItem;
import com.example.restaurant.model.ShoppingCart;
import com.example.restaurant.service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderItemService implements IOrderItemService {

    @Autowired
    IOrderItemDao orderItemDao;
    @Autowired
    IDishesDao dishesDao;

    @Override
    public OrderItem newOrderItem(int dishesId, int customerId, int count) {
        OrderItem orderItem = new OrderItem();
        Dishes dishes = dishesDao.findById(dishesId);
        orderItem.setDishesId(dishesId);
        orderItem.setCustomerId(customerId);
        orderItem.setPrice(dishes.getPrice());
        orderItem.setCount(count);
        orderItem.setTotal(dishes.getPrice() * count);
        orderItem.setTime(new Date());
        orderItem.setStatus("已下单");
        orderItem.setCookId(-1);
        orderItem.setHistoryOrderItem(0);
        orderItemDao.save(orderItem);
        return orderItem;
    }

    @Override
    public void finialOrderItem(int Id, int cookId) {
        OrderItem orderItem = orderItemDao.findById(Id);
        orderItem.setStatus("已完成");
        orderItem.setCookId(cookId);
        orderItemDao.save(orderItem);
    }

    @Override
    public Page<OrderItem> getOrderItemByCustomerId(int customerId, Pageable pageable) {
        return orderItemDao.findByCustomerId(customerId,pageable);
    }

    @Override
    public Page<OrderItem> getOrderItemByStatus(String status, Pageable pageable) {
        return orderItemDao.findByStatus(status,pageable);
    }

    @Override
    public OrderItem getOrderItemByDishesIdAndCustomerId(int dishesId, int customerId) throws Exception{
        OrderItem orderItem = orderItemDao.findByCustomerIdAndDishesId(customerId, dishesId);
        if(orderItem == null){
            throw new Exception("该菜品并没有被下单");
        }
        return orderItem;
    }


    @Override
    public Page<OrderItem> getOrderItem(Pageable pageable) {
        return orderItemDao.findAll(pageable);
    }

    @Override
    public OrderItem reduceOrderItemByCustomer(int dishesId, int customerId, int count){
        OrderItem orderItem = orderItemDao.findByCustomerIdAndDishesId(customerId,dishesId);
        if(count < orderItem.getCount()){
            orderItem.setCount(orderItem.getCount() - count);
        }else{
            orderItem.setCount(0);
            orderItem.setStatus("已删除");
        }
        orderItemDao.save(orderItem);
        return orderItem;
    }

    @Override
    public List<OrderItem> getOrderItemByCustomerId(int customerId) {
        return orderItemDao.findByCustomerId(customerId);
    }

    @Override
    public Page<OrderItem> getOrderItemByCustomerIdAndHistoryOrderItem(int customerId,Pageable pageable) {
        return orderItemDao.findByCustomerIdAndHistoryOrderItem(customerId,0,pageable);
    }


}
