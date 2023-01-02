package com.example.restaurant.controller;

import com.example.restaurant.model.User;
import com.example.restaurant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.StyleSheet;

@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping(path = "/userLisePage",method = RequestMethod.POST)
    public String userListPage(
            @RequestParam(name = "number")
            int n,Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登陆");
            return "login";
        }
        PageRequest of = PageRequest.of(n, 10);
        Page<User> userList = userService.getUserList(of);
        model.addAttribute("userList",userList);
        return "userList";
    }

    @RequestMapping(path = "/changePassword",method = RequestMethod.POST)
    public String changePassword(
            @RequestParam(name = "code")
            String code,
            @RequestParam(name = "password")
            String password,Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登陆");
            return "login";
        }
        try {
            userService.resetPassword(user.getPhone(),code,password);
        } catch (Exception e) {
            model.addAttribute("tip",e.getMessage());
            return "changeUserInformation";
        }
        model.addAttribute("tip","更改成功");
        return "changeUserInformation";
    }

    @RequestMapping(path = "/addUser",method = RequestMethod.POST)
    public String addUser(
            @RequestParam(name = "name")
            String name,
            @RequestParam(name = "password")
            String password,
            @RequestParam(name = "identity")
            String identity,
            @RequestParam(name = "cellphone")
            String cellphone,Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登陆");
            return "login";
        }
        try {
            userService.addUser(name,password,cellphone,identity);
            model.addAttribute("tip","添加成功");
            return "newUser";
        } catch (Exception e) {
            model.addAttribute("tip",e.getMessage());
            return "newUser";
        }
    }

    @RequestMapping(path = "/deleteUser",method = RequestMethod.POST)
    public String deleteUser(
            @RequestParam(name = "id")
            int id,Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","你已退出系统，请重新登陆");
            return "login";
        }
        userService.deleteUser(id);
        model.addAttribute("tip","删除成功");
        return "deleteUser";
    }
}
