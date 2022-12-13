package com.example.restaurant.controller;

import com.example.restaurant.model.Customer;
import com.example.restaurant.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CustomerController {

    @Autowired
    ICustomerService customerService;

    @RequestMapping(path = "/customer",method = RequestMethod.POST)
    public String customer(
            @RequestParam(name = "name")
            String name,
            @RequestParam(name = "password")
            String password,
            @RequestParam(name = "count")
            int count,
            @RequestParam(name = "table")
            int tableId, HttpSession session){
        Customer customer = customerService.newCustomer(name, password, count, tableId);
        session.setAttribute("customer",customer);
        return "dishesIndex";
    }
}
