package com.example.restaurant.controller;

import com.example.restaurant.model.Customer;
import com.example.restaurant.model.Dishes;
import com.example.restaurant.model.OrderList;
import com.example.restaurant.model.User;
import com.example.restaurant.service.IDishesService;
import com.example.restaurant.service.IOrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @Autowired
    IDishesService dishesService;
    @Autowired
    IOrderListService orderListService;


    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String index(){
        return "login";
    }

    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(path = "/loading",method = RequestMethod.GET)
    public String loading(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        return "loading";
    }

    @RequestMapping(path = "/customer",method = RequestMethod.GET)
    public String customer(){
        return "customer";
    }

    @RequestMapping(path = "/dishesIndex",method = RequestMethod.GET)
    public String dishesIndex(Model model,HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "customer";
        }
        PageRequest of = PageRequest.of(0,10);
        Page<Dishes> dishesList = dishesService.getDishesListByCategoryId(1,of);
        model.addAttribute("dishesList",dishesList);
        return "dishesIndex";
    }

    @RequestMapping(path = "/lookingFor",method = RequestMethod.POST)
    public String lookingFor(Model model,HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "customer";
        }
        return "lookingFor";
    }

    @RequestMapping(path = "/shoppingCart",method = RequestMethod.POST)
    public String shoppingCart(Model model,HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "customer";
        }
        PageRequest of = PageRequest.of(0,10);
        Page<OrderList> orderLists = orderListService.getOrderListByCustomerId(customer.getId(),of);
        model.addAttribute("orderList",orderLists);
        return "shoppingCart";
    }

    @RequestMapping(path = "/orderedMenu",method = RequestMethod.GET)
    public String orderedMenu(Model model,HttpSession session){
//        Customer customer = (Customer) session.getAttribute("customer");
//        if(customer == null){
//            model.addAttribute("error","您已退出系统，请重新登陆");
//            return "customer";
//        }
        PageRequest of = PageRequest.of(0,10);
        Page<OrderList> orderLists = orderListService.getOrderListByCustomerId(1,of);
        model.addAttribute("orderList",orderLists);
        return "orderedMenu";
    }

    @RequestMapping(path = "/cookBoard",method = RequestMethod.GET)
    public String cookBoard(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登陆");
            return "login";
        }
        return "cookBoard";
    }

    @RequestMapping(path = "/startCooking",method = RequestMethod.GET)
    public String startCooking(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登陆");
            return "login";
        }
        return "startCooking";
    }

    @RequestMapping(path = "/rest",method = RequestMethod.GET)
    public String rest(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登陆");
            return "login";
        }
        return "rest";
    }

    @RequestMapping(path = "/userBoard",method = RequestMethod.GET)
    public String userBoard(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登陆");
            return "login";
        }
        return "userBoard";
    }
}