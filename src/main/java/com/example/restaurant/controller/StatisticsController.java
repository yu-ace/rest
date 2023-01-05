package com.example.restaurant.controller;

import com.example.restaurant.model.Statistics;
import com.example.restaurant.model.User;
import com.example.restaurant.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StatisticsController {

    @Autowired
    IStatisticsService statisticsService;

    @RequestMapping(path = "/statisticsListPage",method = RequestMethod.POST)
    public String statisticsListPage(
            @RequestParam(name = "number")
            int n, Model model){
        PageRequest of = PageRequest.of(n, 10);
        Page<Statistics> statisticsList = statisticsService.getStatisticsList(of);
        model.addAttribute("statisticsList",statisticsList);
        return "users/orderItemForUser";
    }

    @RequestMapping(path = "/statisticsListOrderBy",method = RequestMethod.POST)
    public String statisticsListOrderBy(
            @RequestParam(name = "change")
            int n,Model model){
        List<Statistics> statisticsPage = null;
        switch (n){
            case 1:
                statisticsPage = statisticsService.getStatisticsListOrderByCount();
                break;
            case 2:
                statisticsPage = statisticsService.getStatisticsListOrderByPrice();
                break;
            case 3:
                statisticsPage = statisticsService.getStatisticsListOrderByTotal();
                break;
        }
        model.addAttribute("statisticsList",statisticsPage);
        return "users/orderItemForUser";
    }
}
