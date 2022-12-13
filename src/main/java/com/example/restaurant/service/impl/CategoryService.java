package com.example.restaurant.service.impl;

import com.example.restaurant.dao.ICategoryDao;
import com.example.restaurant.model.Category;
import com.example.restaurant.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    ICategoryDao categoryDao;

    @Override
    public Category newCategory(String name) {
        Category category = new Category();
        category.setName(name);
        categoryDao.save(category);
        return category;
    }

    @Override
    public Page<Category> getCategory(Pageable pageable) {
        return categoryDao.findAll(pageable);
    }
}
