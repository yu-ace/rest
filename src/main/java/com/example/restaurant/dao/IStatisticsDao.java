package com.example.restaurant.dao;

import com.example.restaurant.model.Statistics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStatisticsDao extends JpaRepository<Statistics,Integer> {
    Statistics findByDishesId(int dishesId);
}
