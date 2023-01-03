package com.example.restaurant.service;

import com.example.restaurant.model.Customer;
import com.example.restaurant.model.User;

public interface ICustomerService {
    public Customer newCustomer(String cellphone);
    Customer getCustomerById(int id);
    Customer getCustomerByName(String name);
    Customer getCustomerByPhone(String phone);
}
