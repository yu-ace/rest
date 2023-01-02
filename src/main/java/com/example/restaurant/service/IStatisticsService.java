package com.example.restaurant.service;

import com.example.restaurant.model.Statistics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStatisticsService {
    Statistics newStatistics(int dishesId,int count);
    Statistics addStatistics(int dishesId,int count);
    Statistics reduceStatistics(int dishesId,int count);
    Statistics getStatisticsByDishesId(int dishesId);
    Page<Statistics> getStatisticsList(Pageable pageable);
    List<Statistics> getStatisticsListOrderByCount();
    List<Statistics> getStatisticsListOrderByPrice();
    List<Statistics> getStatisticsListOrderByTotal();
}
