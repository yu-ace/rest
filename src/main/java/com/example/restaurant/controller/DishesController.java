package com.example.restaurant.controller;

import com.example.restaurant.model.Customer;
import com.example.restaurant.model.Dishes;
import com.example.restaurant.service.IDishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class DishesController {

    @Autowired
    IDishesService dishesService;

    @RequestMapping(path = "dishesListPage",method = RequestMethod.POST)
    public String dishesListPage(
            @RequestParam(name = "number")
            int n,Model model){
        PageRequest of = PageRequest.of(n,10);
        Page<Dishes> dishesList = dishesService.getDishesList(of);
        model.addAttribute("dishesList",dishesList);
        return "users/dishes";
    }

    @RequestMapping(path = "dishesByName",method = RequestMethod.POST)
    public String dishesByName(
            @RequestParam(name = "name")
            String name,Model model){
        Dishes dishesByName = dishesService.getDishesByName(name);
        model.addAttribute("dishesList",dishesByName);
        return "users/dishes";
    }

    @RequestMapping(path = "dishesByCategory",method = RequestMethod.POST)
    public String dishesByCategory(
            @RequestParam(name = "categoryId")
            int categoryId,
            @RequestParam(name = "n")
            int n, Model model){
        PageRequest of = PageRequest.of(n, 10);
        Page<Dishes> dishesListByCategoryId = dishesService.getDishesListByCategoryId(categoryId, of);
        model.addAttribute("dishesList",dishesListByCategoryId);
        return "users/dishes";
    }

    @RequestMapping(path = "addDishesList",method = RequestMethod.POST)
    public String addDishesList(
            @RequestParam(name = "categoryId")
            int categoryId,
            @RequestParam(name = "name")
            String name,
            @RequestParam(name = "price")
            double price,
            Model model){
        dishesService.newDishes(name,price,categoryId);
        model.addAttribute("tip","添加成功");
        return "users/addDishesList";
    }

    @RequestMapping(path = "/dishesListByCategoryId",method = RequestMethod.POST)
    public String dishesListByCategoryId(
            @RequestParam(name = "categoryId")
            int categoryId,Model model,HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("tip","你已退出系统，请重新登录");
            return "customer";
        }
        PageRequest of = PageRequest.of(0, 10);
        Page<Dishes> dishesPage = dishesService.getDishesListByCategoryId(categoryId, of);
        model.addAttribute("dishesList",dishesPage);
        return "customerIndex";
    }

    @RequestMapping(path = "/dishesListPageByCategoryId",method = RequestMethod.POST)
    public String dishesListPageByCategoryId(
            @RequestParam(name = "categoryId")
            int categoryId,
            @RequestParam(name = "number")
            int n,Model model,HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            model.addAttribute("tip","你已退出系统，请重新登录");
            return "customer";
        }
        PageRequest of = PageRequest.of(n, 10);
        Page<Dishes> dishesPage = dishesService.getDishesListByCategoryId(categoryId, of);
        model.addAttribute("dishesList",dishesPage);
        return "customerIndex";
    }

    @ResponseBody
    @RequestMapping(path = "/dishes",method = RequestMethod.GET)
    public Dishes dishes(int dishesId){
        Dishes dishes = dishesService.getDishesById(dishesId);
        return dishes;
    }

    @RequestMapping(path = "dishesByCategoryId",method = RequestMethod.POST)
    public String dishesByCategoryId(
            @RequestParam(name = "categoryId")
            int categoryId,Model model){
        PageRequest of = PageRequest.of(0, 10);
        Page<Dishes> dishesListByCategoryId = dishesService.getDishesListByCategoryId(categoryId, of);
        model.addAttribute("dishesList",dishesListByCategoryId);
        return "users/addOrder";
    }

    @RequestMapping(path = "/dishesByCategoryIdPage",method = RequestMethod.POST)
    public String dishesByCategoryIdPage(
            @RequestParam(name = "categoryId")
            int categoryId,
            @RequestParam(name = "number")
            int n,Model model){
        PageRequest of = PageRequest.of(n, 10);
        Page<Dishes> dishesPage = dishesService.getDishesListByCategoryId(categoryId, of);
        model.addAttribute("dishesList",dishesPage);
        return "users/addOrder";
    }

}
