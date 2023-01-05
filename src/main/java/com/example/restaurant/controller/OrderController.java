package com.example.restaurant.controller;

import com.example.restaurant.model.*;
import com.example.restaurant.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    IOrderService orderService;
    @Autowired
    IDishesService dishesService;
    @Autowired
    IShoppingCartService shoppingCartService;
    @Autowired
    IOrderItemService orderItemService;
    @Autowired
    IStatisticsService statisticsService;

    @RequestMapping(path = "/order",method = RequestMethod.POST)
    public String orderMenu(
            @RequestParam(name = "orderList")
            String orderLists,
            @RequestParam(name = "counts")
            String counts, Model model, HttpServletRequest request, HttpSession session){
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        int tableId = (int) session.getAttribute("tableId");
        String[] dishesIds = orderLists.split(",");
        String[] dishCounts = counts.split(",");
        List<OrderItem> orderItemLists = new ArrayList<>();
        for(int i = 0;i < dishesIds.length;i++){
            int dishesId = Integer.parseInt(dishesIds[i]);
            int dishesCount = Integer.parseInt(dishCounts[i]);
            Dishes dishes = dishesService.getDishesById(dishesId);
            double dishPrice = dishes.getPrice();
            OrderItem orderItem = new OrderItem();
            orderItem.setCustomerId(customer.getId());
            orderItem.setDishesId(dishesId);
            orderItem.setCount(dishesCount);
            orderItem.setPrice(dishPrice);
            orderItem.setTotal(dishesCount * dishPrice);
            orderItem.setStatus("已下单");
            orderItem.setTime(new Date());
            orderItem.setCookId(-1);
            orderItemLists.add(orderItem);
            Statistics statistics = statisticsService.getStatisticsByDishesId(dishesId);
            if(statistics == null){
                statisticsService.newStatistics(dishesId,dishesCount);
            }else{
                statisticsService.addStatistics(dishesId,dishesCount);
            }
            shoppingCartService.deleteShoppingItem(dishesId,customer.getId());
            orderItemService.newOrderItem(dishesId,customer.getId(),dishesCount);
        }
        Order order = orderService.getOrderByCustomerId(customer.getId());
        if(order == null || "已结账".equals(order.getStatus())){
            orderService.createOrder(customer.getId(),orderItemLists,tableId);
        }else{
            orderService.addOrder(customer.getId(),orderItemLists);
        }
        model.addAttribute("tip","下单成功");
        return "customer/customerIndex";
    }

    @RequestMapping(path = "/orderByTableId",method = RequestMethod.POST)
    public String orderByTableId(
            @RequestParam(name= "tableId")
            int tableId, Model model){
        Order order = orderService.getOrderByTableId(tableId);
        if(order == null){
            model.addAttribute("tip","该桌没有需要支付的账单");
            return "users/pay";
        }
        model.addAttribute("order",order);
        return "users/pay";
    }


    @RequestMapping(path = "/payOrder",method = RequestMethod.POST)
    public String payOrder(
            @RequestParam(name = "tableId")
            int tableId,Model model){
        try {
            orderService.payOrder(tableId);
            model.addAttribute("tip","支付成功");
            return "users/pay";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("tip",e.getMessage());
            return "users/pay";
        }

    }
}
