package com.example.restaurant.controller;

import com.example.restaurant.model.OrderItemForCook;
import com.example.restaurant.model.User;
import com.example.restaurant.service.IOrderItemForCookService;
import com.example.restaurant.service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class OrderItemCookController {

    @Autowired
    IOrderItemForCookService orderListCookService;
    @Autowired
    IOrderItemService orderListService;

    @RequestMapping(path = "startCooking",method = RequestMethod.POST)
    public String startCooking(
            @RequestParam(name = "category")
            String name, Model model){
        PageRequest of = PageRequest.of(0, 10);
        Page<OrderItemForCook> orderItemForCooks =
                orderListCookService.getOrderListBookByCategoryAndStatus(name, "已下单", of);
        model.addAttribute("orderListForCook",orderItemForCooks);
        return "users/startCooking";
    }

    @RequestMapping(path = "/finishCooking",method = RequestMethod.POST)
    public String finishCooking(
            @RequestParam(name = "id")
            int id, Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        orderListService.finialOrderItem(id, user.getId());
        model.addAttribute("tip","请制作下一道菜");
        return "users/startCooking";
    }

    @RequestMapping(path = "/rest",method = RequestMethod.POST)
    public String rest(){
        return "users/startCooking";
    }
}
