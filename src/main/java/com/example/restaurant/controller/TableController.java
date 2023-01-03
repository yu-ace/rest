package com.example.restaurant.controller;

import com.example.restaurant.model.Table;
import com.example.restaurant.model.User;
import com.example.restaurant.service.ITableService;
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
public class TableController {

    @Autowired
    ITableService tableService;

    @RequestMapping(path = "/tableListPage",method = RequestMethod.POST)
    public String tableListPage(
            @RequestParam(name = "number")
            int n, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        PageRequest of = PageRequest.of(n, 10);
        Page<Table> tableList = tableService.getTableList(0,of);
        model.addAttribute("tableList",tableList);
        return "tableList";
    }

    @RequestMapping(path = "/addTable",method = RequestMethod.POST)
    public String addTable(
            @RequestParam(name = "name")
            String name,Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        tableService.newTable(name);
        model.addAttribute("tip","添加成功");
        return "table";
    }

    @RequestMapping(path = "/reduceTable",method = RequestMethod.POST)
    public String reduceTable(
            @RequestParam(name = "id")
            int id,Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登录");
            return "login";
        }
        tableService.reduceTable(id);
        model.addAttribute("tip","删除成功");
        return "table";
    }

}