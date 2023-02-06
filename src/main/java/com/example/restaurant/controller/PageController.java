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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @Autowired
    IDishesService dishesService;
    @Autowired
    IOrderItemService orderItemService;
    @Autowired
    IUserService userService;
    @Autowired
    IOrderService orderService;
    @Autowired
    IShoppingCartService shoppingCartService;
    @Autowired
    IStatisticsService statisticsService;


    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String index(){
        return "login";
    }

    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(path = "/userBoard",method = RequestMethod.GET)
    public String userBoard(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        return "users/userBoard";
    }

    @RequestMapping(path = "/dishesList",method = RequestMethod.GET)
    public String dishes(Model model){
        PageRequest of = PageRequest.of(0,10);
        Page<Dishes> dishesList = dishesService.getDishesList(of);
        model.addAttribute("dishesList",dishesList);
        return "users/dishes";
    }

    @RequestMapping(path = "/addDishesList",method = RequestMethod.GET)
    public String addDishesList(){
        return "users/addDishesList";
    }


    @RequestMapping(path = "/userList",method = RequestMethod.GET)
    public String userList(Model model){
        PageRequest of = PageRequest.of(0, 10);
        Page<User> userList = userService.getUserList(of);
        model.addAttribute("userList",userList);
        return "users/userList";
    }

    @RequestMapping(path = "/changeUserInformation",method = RequestMethod.GET)
    public String changeUserInformation(){
        return "users/changeUserInformation";
    }

    @RequestMapping(path = "/newUser",method = RequestMethod.GET)
    public String newUser(){
        return "users/newUser";
    }

    @RequestMapping(path = "/deleteUser",method = RequestMethod.GET)
    public String deleteUser(){
        return "users/deleteUser";
    }

    @RequestMapping(path = "/table",method = RequestMethod.GET)
    public String table(){
        return "users/table";
    }

    @RequestMapping(path = "/pay",method = RequestMethod.GET)
    public String bill(Model model){
        PageRequest of = PageRequest.of(0, 10);
        Page<Order> orderList = orderService.getOrderList(of);
        model.addAttribute("orderList",orderList);
        return "users/pay";
    }

    @RequestMapping(path = "/orderItemForUser",method = RequestMethod.GET)
    public String orderItemForUser(Model model){
        PageRequest of = PageRequest.of(0, 10);
        Page<Statistics> statisticsList = statisticsService.getStatisticsList(of);
        model.addAttribute("statisticsList",statisticsList);
        return "users/orderItemForUser";
    }

    @RequestMapping(path = "/addOrder",method = RequestMethod.GET)
    public String changeOrder(){
        return "users/addOrder";
    }

    @RequestMapping(path = "/reduceOrder",method = RequestMethod.GET)
    public String reduceOrder(){
        return "users/reduceOrder";
    }



    //厨师系统
    @RequestMapping(path = "/cookBoard",method = RequestMethod.GET)
    public String cookBoard(Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        return "users/cookBoard";
    }

    @RequestMapping(path = "/startCooking",method = RequestMethod.GET)
    public String startCooking(){
        return "users/startCooking";
    }

    @RequestMapping(path = "/rest",method = RequestMethod.GET)
    public String rest(){
        return "users/rest";
    }






    //顾客端


    @RequestMapping(path = "/customer",method = RequestMethod.GET)
    public String customer(){
        return "customer";
    }

    @RequestMapping(path = "/customerIndex",method = RequestMethod.GET)
    public String dishesIndex(Model model){
        PageRequest of = PageRequest.of(0,10);
        Page<Dishes> dishesList = dishesService.getDishesListByCategoryId(1,of);
        model.addAttribute("dishesList",dishesList);
        return "customer/customerIndex";
    }

    @RequestMapping(path = "/lookingFor",method = RequestMethod.GET)
    public String lookingFor(Model model){
        PageRequest of = PageRequest.of(0,10);
        Page<Dishes> dishesList = dishesService.getDishesListByCategoryId(1,of);
        model.addAttribute("dishesList",dishesList);
        return "customer/lookingFor";
    }

    @RequestMapping(path = "/shoppingCart",method = RequestMethod.GET)
    public String shoppingCart(Model model,HttpServletRequest request){
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        PageRequest of = PageRequest.of(0,10);
        Page<ShoppingCart> shoppingItemPage = shoppingCartService.getShoppingItemPage(customer.getId(), 0, of);
        model.addAttribute("shoppingItem",shoppingItemPage);
        return "customer/shoppingCart";
    }

    @RequestMapping(path = "/orderedMenu",method = RequestMethod.GET)
    public String orderedMenu(Model model,HttpServletRequest request){
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        PageRequest of = PageRequest.of(0,10);
        Page<OrderItem> orderLists = orderItemService.getOrderItemByCustomerIdAndHistoryOrderItem(customer.getId(),of);
        model.addAttribute("orderList",orderLists);
        return "customer/orderedMenu";
    }




}