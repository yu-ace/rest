package com.example.restaurant.controller;

import com.example.restaurant.model.Customer;
import com.example.restaurant.model.Dishes;
import com.example.restaurant.model.OrderList;
import com.example.restaurant.service.IBillService;
import com.example.restaurant.service.IDishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BillController {

    @Autowired
    IBillService billService;
    @Autowired
    IDishesService dishesService;

    @RequestMapping(path = "/order",method = RequestMethod.POST)
    public String orderMenu(
            @RequestParam(name = "orderList")
            String orderLists,
            @RequestParam(name = "counts")
            String counts,Model model, HttpSession session){
//        Customer customer = (Customer) session.getAttribute("customer");
//        if(customer == null){
//            model.addAttribute("tip","");
//            return "customer";
//        }
        String[] dishIds = orderLists.split(",");
        String[] dishCounts = counts.split(",");
        List<OrderList> orderLists1 = new ArrayList<>();
        for(int i = 0;i < dishIds.length;i++){
            int dishId = Integer.parseInt(dishIds[i]);
            int dishCount = Integer.parseInt(dishCounts[i]);
            Dishes dishes = dishesService.getDishesById(dishId);
            double dishPrice = dishes.getPrice();
            OrderList orderList = new OrderList();
            //todo 后面要改
            orderList.setCustomerId(5);
            orderList.setDishesId(dishId);
            orderList.setCount(dishCount);
            orderList.setUnitPrice(dishPrice);
            orderList.setPrice(dishCount * dishPrice);
            orderList.setStatus("已下单");
            orderList.setTime(new Date());
            orderList.setCookId(-1);
            orderLists1.add(orderList);
        }
        //todo 后面要改
        billService.createOrder(5,orderLists1);
        return "orderedMenu";
    }

}
