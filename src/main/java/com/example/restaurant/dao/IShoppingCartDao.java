package com.example.restaurant.dao;

import com.example.restaurant.model.ShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShoppingCartDao extends JpaRepository<ShoppingCart,Integer> {
    Page<ShoppingCart> findByCustomerIdAndStatus(int customerId, int status,Pageable pageable);
    ShoppingCart findByDishesIdAndCustomerId(int dishesId,int customerId);
    ShoppingCart findByDishesIdAndCustomerIdAndStatus(int dishesId,int customerId,int status);
}
