package com.example.restaurant.controller;

import cn.hutool.crypto.Mode;
import com.example.restaurant.model.OrderListCook;
import com.example.restaurant.model.User;
import com.example.restaurant.service.IOrderListCookService;
import com.example.restaurant.service.IOrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class OrderListCookController {

    @Autowired
    IOrderListCookService orderListCookService;
    @Autowired
    IOrderListService orderListService;

    @RequestMapping(path = "/startCooking",method = RequestMethod.POST)
    public String startCooking(
            @RequestParam(name = "category")
            String category, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登录");
            return "login";
        }
        PageRequest of = PageRequest.of(0,10);
        Page<OrderListCook> orderListCookPage =
                orderListCookService.getOrderListBookByCategoryAndStatus(category,"已下单",of);
        model.addAttribute("orderListForCook",orderListCookPage);
        return "startCooking";
    }

    @RequestMapping(path = "/finishCooking",method = RequestMethod.POST)
    public String finishCooking(
            @RequestParam(name = "id")
            int id, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登录");
            return "login";
        }
        orderListService.finialOrderList(id, user.getId());
        model.addAttribute("tip","本菜品已完成，请制作下一道菜");
        return "startCooking";
    }

    @RequestMapping(path = "/rest",method = RequestMethod.POST)
    public String rest(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登录");
            return "login";
        }
        return "startCooking";
    }
}
