package com.example.restaurant.service.impl;

import com.example.restaurant.dao.ICustomerDao;
import com.example.restaurant.model.Customer;
import com.example.restaurant.model.User;
import com.example.restaurant.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    ICustomerDao customerDao;

    @Override
    public Customer newCustomer(String cellphone) {
        Customer customer = new Customer();
        customer.setPhone(cellphone);
        String a = "qwertyuipkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM0123456789";
        String name = "";
        Random random = new Random();
        for(int i = 0;i < 9;i++){
            name = name + a.charAt(random.nextInt(a.length() - 1));
        }
        customer.setName(name);
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
    public Customer getCustomerByPhone(String phone) {
        return customerDao.findByPhone(phone);
    }
}
