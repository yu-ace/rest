package com.example.restaurant.dao;

import com.example.restaurant.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryDao extends JpaRepository<Category,Integer> {
    Category getCategoryById(int id);
}
