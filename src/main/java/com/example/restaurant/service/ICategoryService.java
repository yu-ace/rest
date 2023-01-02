package com.example.restaurant.service;

import com.example.restaurant.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {
    Category getCategoryById(int id);
    Category newCategory(String name);
    Page<Category> getCategory(Pageable pageable);
}
