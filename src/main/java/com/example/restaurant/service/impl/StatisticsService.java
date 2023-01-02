package com.example.restaurant.service.impl;

import com.example.restaurant.dao.ICategoryDao;
import com.example.restaurant.dao.IDishesDao;
import com.example.restaurant.dao.IStatisticsDao;
import com.example.restaurant.model.Dishes;
import com.example.restaurant.model.Statistics;
import com.example.restaurant.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService implements IStatisticsService {

    @Autowired
    IStatisticsDao statisticsDao;
    @Autowired
    IDishesDao dishesDao;

    @Override
    public Statistics newStatistics(int dishesId, int count) {
        Statistics statistics = new Statistics();
        statistics.setDishesId(dishesId);
        Dishes dishes = dishesDao.findById(dishesId);
        statistics.setCategoryId(dishes.getCategoryId());
        statistics.setCount(count);
        statistics.setPrice(dishes.getPrice());
        statistics.setTotal(count * dishes.getPrice());
        statisticsDao.save(statistics);
        return statistics;
    }

    @Override
    public Statistics addStatistics(int dishesId, int count) {
        Statistics statistics = statisticsDao.findByDishesId(dishesId);
            statistics.setCount(statistics.getCount() + count);
            statistics.setTotal(statistics.getTotal() + statistics.getPrice() * count);
            statisticsDao.save(statistics);
            return statistics;
    }

    @Override
    public Statistics reduceStatistics(int dishesId, int count) {
        Statistics statistics = statisticsDao.findByDishesId(dishesId);
        statistics.setCount(statistics.getCount() - count);
        statistics.setTotal(statistics.getTotal() - statistics.getPrice() * count);
        statisticsDao.save(statistics);
        return statistics;
    }

    @Override
    public Statistics getStatisticsByDishesId(int dishesId) {
        return statisticsDao.findByDishesId(dishesId);
    }

    @Override
    public Page<Statistics> getStatisticsList(Pageable pageable) {
        return statisticsDao.findAll(pageable);
    }

    @Override
    public List<Statistics> getStatisticsListOrderByCount() {
        return statisticsDao.findAll(Sort.by("count").descending());
    }

    @Override
    public List<Statistics> getStatisticsListOrderByPrice() {
        return statisticsDao.findAll(Sort.by("price").descending());
    }

    @Override
    public List<Statistics> getStatisticsListOrderByTotal() {
        return statisticsDao.findAll(Sort.by("total").descending());
    }


}
