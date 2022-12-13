package com.example.restaurant.dao;

import com.example.restaurant.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerDao extends JpaRepository<Customer,Integer> {
    Customer findByName(String name);
    Customer findByTableId(int tableId);
    Customer findById(int id);
}
