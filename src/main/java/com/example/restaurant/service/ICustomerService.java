package com.example.restaurant.service;

import com.example.restaurant.model.Customer;

public interface ICustomerService {
    Customer newCustomer(String name,String password,int count,int tableId);
    Customer getCustomerById(int id);
    Customer getCustomerByName(String name);
    Customer getCustomerByTableId(int tableId);
}
