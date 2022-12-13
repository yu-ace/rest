package com.example.restaurant.controller;

import com.example.restaurant.dao.IDishesDao;
import com.example.restaurant.dao.IOrderListDao;
import com.example.restaurant.model.Customer;
import com.example.restaurant.model.Dishes;
import com.example.restaurant.model.OrderList;
import com.example.restaurant.service.IDishesService;
import com.example.restaurant.service.IOrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class OrderListController {

    @Autowired
    IOrderListService orderListService;
    @Autowired
    IDishesService dishesService;

    @RequestMapping(path = "/addOrderList",method = RequestMethod.POST)
    public String addOrderList(
            @RequestParam(name = "id")
            int id,
            @RequestParam(name = "count")
            int count, Model model, HttpSession session){
        Customer customer =(Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "customer";
        }
        Dishes dishes = dishesService.getDishesById(id);
        if(dishes == null){
            model.addAttribute("tip","对不起，本店没有该菜品");
            return "dishesIndex";
        }
        orderListService.newOrderList(id,customer.getId(),dishes.getPrice(),count,(dishes.getPrice()*count));
        model.addAttribute("tip","添加成功");
        return "dishesIndex";
    }

    @RequestMapping(path = "/deleteOrderList",method = RequestMethod.POST)
    public String deleteOrderList(
            @RequestParam(name = "id")
            int id,
            @RequestParam(name = "count")
            int count, Model model, HttpSession session){
        Customer customer =(Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "customer";
        }
        PageRequest of = PageRequest.of(0,10);
        Page<OrderList> orderLists = orderListService.getOrderListByCustomerId(customer.getId(),of);
        if(orderLists == null){
            return "shoppingCart";
        }
        orderListService.reduceOrderListByCustomer(id,customer.getId(),count);
        model.addAttribute("tip","删除成功");
        return "shoppingCart";
    }

    @RequestMapping(path = "/OrderListPage",method = RequestMethod.POST)
    public String OrderListPage(
            @RequestParam(name = "number")
            int n,Model model, HttpSession session){
        Customer customer =(Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "customer";
        }
        PageRequest of = PageRequest.of(n,10);
        Page<OrderList> orderLists = orderListService.getOrderListByCustomerId(customer.getId(),of);
        if(orderLists == null){
            return "shoppingCart";
        }
        return "shoppingCart";
    }
}
