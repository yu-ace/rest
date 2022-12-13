package com.example.restaurant.service.impl;

import com.example.restaurant.dao.IBillDao;
import com.example.restaurant.dao.IOrderListDao;
import com.example.restaurant.model.Bill;
import com.example.restaurant.model.OrderList;
import com.example.restaurant.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService implements IBillService {

    @Autowired
    IBillDao billDao;
    @Autowired
    IOrderListDao orderListDao;

    @Override
    public Bill newBill(int customerId, double total) {
        Bill bill = new Bill();
        bill.setCustomerId(customerId);
        bill.setTotal(total);
        billDao.save(bill);
        return bill;
    }

    @Override
    public Bill getBillListByCustomerId(int id) {
        return billDao.findByCustomerId(id);
    }

    @Override
    public Bill createOrder(int customerId, List<OrderList> orderItems) {
        Bill bill = new Bill();
        bill.setCustomerId(customerId);
        double sum = 0;
        for(OrderList orderList:orderItems){
            sum = sum + orderList.getPrice();
        }
        bill.setTotal(sum);
        Bill bill1 = billDao.save(bill);
        for(OrderList orderList:orderItems){
            orderList.setBillId(bill1.getId());
            orderListDao.save(orderList);
        }
        return bill;

    }
}
