package com.example.restaurant.controller;

import com.example.restaurant.model.*;
import com.example.restaurant.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class OrderItemController {

    @Autowired
    IOrderItemService orderItemService;
    @Autowired
    IDishesService dishesService;
    @Autowired
    IShoppingCartService shoppingCartService;
    @Autowired
    ICustomerService customerService;
    @Autowired
    IOrderService orderService;
    @Autowired
    IStatisticsService statisticsService;


    @RequestMapping(path = "/addShoppingCart",method = RequestMethod.POST)
    public String addShoppingCart(
            @RequestParam(name = "id")
            int dishesId,
            @RequestParam(name = "count")
            int count, Model model, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("tip","你已退出系统，请重新登录");
            return "customer";
        }
        shoppingCartService.createShoppingItem(dishesId,count,customer.getId());
        model.addAttribute("tip","添加成功");
        return "customerIndex";
    }

    @RequestMapping(path = "/shoppingItemPage",method = RequestMethod.POST)
    public String shoppingItemPage(
            @RequestParam(name = "number")
            int n,Model model,HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("tip","你已退出系统，请重新登陆");
            return "customer";
        }
        PageRequest of = PageRequest.of(n, 10);
        Page<ShoppingCart> shoppingItemPage = shoppingCartService.getShoppingItemPage(customer.getId(), 0, of);
        model.addAttribute("shoppingItem",shoppingItemPage);
        return "shoppingCart";
    }

    @RequestMapping(path = "/reduceShoppingItem",method = RequestMethod.POST)
    public String reduceShoppingItem(
            @RequestParam(name = "id")
            int dishesId,
            @RequestParam(name = "count")
            int count,Model model,HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("tip","你已退出系统，请重新登陆");
            return "customer";
        }
        try {
            shoppingCartService.changeShoppingItem(dishesId,customer.getId(),count);
            model.addAttribute("tip","更改成功");
            return "shoppingCart";
        } catch (Exception e) {
            model.addAttribute("tip",e.getMessage());
            return "shoppingCart";
        }
    }

    @RequestMapping(path = "/OrderListPage",method = RequestMethod.POST)
    public String OrderListPage(
            @RequestParam(name= "number")
            int n,Model model,HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("tip","你已退出系统，请重新登录");
            return "customer";
        }
        PageRequest of = PageRequest.of(n, 10);
        Page<OrderItem> orderItemPage = orderItemService.
                getOrderItemByCustomerIdAndHistoryOrderItem(customer.getId(), of);
        model.addAttribute("orderList",orderItemPage);
        return "orderedMenu";
    }

    @RequestMapping(path = "/orderedByCustomer",method = RequestMethod.POST)
    public String orderedByCustomer(
            @RequestParam(name = "customerId")
            int customerId,Model model,HttpSession session,HttpSession session1){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        PageRequest of = PageRequest.of(0, 10);
        Page<OrderItem> orderItems = orderItemService.getOrderItemByCustomerId(customerId, of);
        model.addAttribute("orderItems",orderItems);
        session1.setAttribute("customer",customerService.getCustomerById(customerId));
        return "orderedByCustomer";
    }

    @RequestMapping(path = "/orderItemsPage",method = RequestMethod.POST)
    public String orderItemsPage(
            @RequestParam(name = "number")
            int n,Model model,HttpSession session,HttpSession session1){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        Customer customer = (Customer) session1.getAttribute("customer");
        PageRequest of = PageRequest.of(n, 10);
        Page<OrderItem> orderItems = orderItemService.getOrderItemByCustomerId(customer.getId(), of);
        model.addAttribute("orderItems",orderItems);
        return "orderedByCustomer";
    }

    @RequestMapping(path = "/addOrderItem",method = RequestMethod.POST)
    public String addOrderItem(
            @RequestParam(name = "dishesId")
            int dishesId,
            @RequestParam(name = "number")
            int n,Model model,HttpSession session,HttpSession session1){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        Customer customer = (Customer) session1.getAttribute("customer");
        orderItemService.newOrderItem(dishesId,customer.getId(),n);
        List<OrderItem> orderItems = new ArrayList<>();
        Dishes dishes = dishesService.getDishesById(dishesId);
        OrderItem orderItem = new OrderItem();
        orderItem.setCustomerId(customer.getId());
        orderItem.setDishesId(dishesId);
        orderItem.setCount(n);
        orderItem.setPrice(dishes.getPrice());
        orderItem.setTotal(n * dishes.getPrice());
        orderItem.setStatus("已下单");
        orderItem.setTime(new Date());
        orderItem.setCookId(-1);
        orderItems.add(orderItem);
        orderService.addOrder(customer.getId(),orderItems);
        Statistics statistics = statisticsService.getStatisticsByDishesId(dishesId);
        if(statistics == null){
            statisticsService.newStatistics(dishesId,n);
        }else{
            statisticsService.addStatistics(dishesId,n);
        }
        model.addAttribute("tip","添加成功");
        return "addOrder";
    }

    @RequestMapping(path = "/reduceOrderItem",method = RequestMethod.POST)
    public String reduceOrderItem(
            @RequestParam(name = "dishesId")
            int dishesId,
            @RequestParam(name = "count")
            int count,Model model,HttpSession session,HttpSession session1){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        Customer customer = (Customer) session1.getAttribute("customer");
        List<OrderItem> orderItems = new ArrayList<>();
        Dishes dishes = dishesService.getDishesById(dishesId);
        try {
            OrderItem orderItem = orderItemService.getOrderItemByDishesIdAndCustomerId(dishesId,customer.getId());
            orderItemService.reduceOrderItemByCustomer(dishesId,customer.getId(),count);
            if(count < orderItem.getCount()){
                orderItem.setCount(count);
                orderItem.setTotal(count * dishes.getPrice());
            }else{
                orderItem.setCount(orderItem.getCount());
                orderItem.setTotal(orderItem.getCount() * dishes.getPrice());
            }
            orderItems.add(orderItem);
            orderService.reduceOrder(customer.getId(),orderItems);
            Statistics statistics = statisticsService.getStatisticsByDishesId(dishesId);
            if(statistics == null){
                statisticsService.newStatistics(dishesId,count);
            }else{
                statisticsService.reduceStatistics(dishesId,count);
            }
            model.addAttribute("tip","删除成功");
            return "reduceOrder";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("tip",e.getMessage());
            return "reduceOrder";
        }
    }
}
