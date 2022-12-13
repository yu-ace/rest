package com.example.restaurant.controller;

import com.example.restaurant.model.Customer;
import com.example.restaurant.model.Dishes;
import com.example.restaurant.service.IDishesService;
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
public class DishesController {

    @Autowired
    IDishesService dishesService;


    @RequestMapping(path = "/lookingForSomething",method = RequestMethod.POST)
    public String lookingForSomething(
            @RequestParam(name = "dishes")
            String dishesName,Model model,HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "customer";
        }
        Dishes dishes = dishesService.getDishesByName(dishesName);
        if(dishes == null){
            model.addAttribute("tip","对不起，本店没有该菜品");
            return "lookingFor";
        }else{
            model.addAttribute("dishesList",dishes);
            return "lookingFor";
        }
    }

    @RequestMapping(path = "/dishesListPage",method = RequestMethod.POST)
    public String dishesListPage(
            @RequestParam(name = "number")
            int n,Model model,HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "customer";
        }
        PageRequest of = PageRequest.of(n,10);
        Page<Dishes> dishesList = dishesService.getDishesList(of);
        model.addAttribute("dishesList",dishesList);
        return "lookingFor";
    }

    @RequestMapping(path = "/dishesListByCategoryId",method = RequestMethod.POST)
    public String dishesListByCategoryId(
            @RequestParam(name = "categoryId")
            int categoryId,Model model,HttpSession session,HttpSession session1){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "customer";
        }
        PageRequest of = PageRequest.of(0,10);
        Page<Dishes> dishesList = dishesService.getDishesListByCategoryId(categoryId,of);
        model.addAttribute("dishesList",dishesList);
        session1.setAttribute("categoryId",categoryId);
        return "dishesIndex";
    }

    @RequestMapping(path = "/dishesListPageByCategoryId",method = RequestMethod.POST)
    public String dishesListPageByCategoryId(
            @RequestParam(name = "number")
            int n,Model model,HttpSession session,HttpSession session1){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "customer";
        }
        PageRequest of = PageRequest.of(n,10);
        int categoryId = (int) session1.getAttribute("categoryId");
        Page<Dishes> dishesList = dishesService.getDishesListByCategoryId(categoryId,of);
        model.addAttribute("dishesList",dishesList);
        return "dishesIndex";
    }

}
