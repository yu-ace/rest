package com.example.restaurant.service.impl;

import com.example.restaurant.dao.IDishesDao;
import com.example.restaurant.model.Dishes;
import com.example.restaurant.service.IDishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DishesService implements IDishesService {

    @Autowired
    IDishesDao dishesDao;

    @Override
    public Dishes newDishes(String name, double price, int categoryId) {
        Dishes dishes = new Dishes();
        dishes.setName(name);
        dishes.setPrice(price);
        dishes.setCategoryId(categoryId);
        dishesDao.save(dishes);
        return dishes;
    }

    @Override
    public Dishes getDishesById(int id) {
        return dishesDao.findById(id);
    }

    @Override
    public Dishes getDishesByName(String name) {
        return dishesDao.findByName(name);
    }

    @Override
    public Page<Dishes> getDishesList(Pageable pageable) {
        return dishesDao.findAll(pageable);
    }

    @Override
    public Page<Dishes> getDishesListByCategoryId(int categoryId, Pageable pageable) {
        return dishesDao.findByCategoryId(categoryId,pageable);
    }
}
