package com.example.restaurant.service.impl;

import com.example.restaurant.dao.ICustomerDao;
import com.example.restaurant.model.Customer;
import com.example.restaurant.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    ICustomerDao customerDao;

    @Override
    public Customer newCustomer(String name, String password, int count, int tableId) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setPassword(password);
        customer.setCount(count);
        customer.setTableId(tableId);
        customerDao.save(customer);
        return customer;
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDao.findById(id);
    }

    @Override
    public Customer getCustomerByName(String name) {
        return customerDao.findByName(name);
    }

    @Override
    public Customer getCustomerByTableId(int tableId) {
        return customerDao.findByTableId(tableId);
    }
}
