package com.example.restaurant.service;

import com.example.restaurant.model.Dishes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDishesService {
    Dishes newDishes(String name,double price, int categoryId);
    Dishes getDishesById(int id);
    Dishes getDishesByName(String name);
    Page<Dishes> getDishesList(Pageable pageable);
    Page<Dishes> getDishesListByCategoryId(int categoryId,Pageable pageable);
}
