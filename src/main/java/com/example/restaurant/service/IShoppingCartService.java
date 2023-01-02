package com.example.restaurant.service;

import com.example.restaurant.model.ShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IShoppingCartService {
    ShoppingCart createShoppingItem(int dishesId,int count,int customerId);
    void changeShoppingItem(int dishesId,int customerId,int count) throws Exception;
    Page<ShoppingCart> getShoppingItemPage(int customerId, int status, Pageable pageable);
    void deleteShoppingItem(int dishesId,int customerId);
}
