package com.example.restaurant.controller;

import com.example.restaurant.model.Customer;
import com.example.restaurant.model.Dishes;
import com.example.restaurant.model.User;
import com.example.restaurant.service.ICustomerService;
import com.example.restaurant.service.IDishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class CustomerController {

    @Autowired
    ICustomerService customerService;
    @Autowired
    IDishesService dishesService;

    @RequestMapping(path= "/regCustomer",method = RequestMethod.POST)
    public String regCustomer(
            @RequestParam(name = "name")
            String name,
            @RequestParam(name = "password")
            String password,
            @RequestParam(name = "count")
            int count,
            @RequestParam(name = "tableId")
            int tableId, HttpSession session){
        Customer customer = customerService.newCustomer(name, password, count, tableId);
        session.setAttribute("customer",customer);
        return "redirect:/customerIndex";
    }

    @RequestMapping(path = "/lookingForSomething",method = RequestMethod.POST)
    public String lookingForSomething(
            @RequestParam(name = "dishesName")
            String dishesName,Model model,HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("tip","你已退出系统，请重新登录");
            return "customer";
        }
        Dishes dishes = dishesService.getDishesByName(dishesName);
        model.addAttribute("dishesList",dishes);
        return "lookingFor";
    }

}
