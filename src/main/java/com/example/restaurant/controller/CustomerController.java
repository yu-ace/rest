package com.example.restaurant.controller;

import com.example.restaurant.model.Customer;
import com.example.restaurant.model.Dishes;
import com.example.restaurant.service.ICustomerService;
import com.example.restaurant.service.IDishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CustomerController {

    @Autowired
    ICustomerService customerService;
    @Autowired
    IDishesService dishesService;

    @RequestMapping(path = "/lookingForSomething",method = RequestMethod.POST)
    public String lookingForSomething(
            @RequestParam(name = "dishesName")
            String dishesName,Model model){
        Dishes dishes = dishesService.getDishesByName(dishesName);
        model.addAttribute("dishesList",dishes);
        return "customer/lookingFor";
    }

}
