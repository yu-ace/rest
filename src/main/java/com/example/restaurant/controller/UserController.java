package com.example.restaurant.controller;

import com.example.restaurant.model.User;
import com.example.restaurant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public String login(
            @RequestParam(name = "name")
            String name,
            @RequestParam(name = "password")
            String password, Model model, HttpSession session){
        User user = userService.getUserByName(name);
        if(user == null){
            model.addAttribute("error","该用户不存在");
            return "login";
        }
        if(user.getIsDelete() == 0){
            model.addAttribute("error","该用户已注销，需重新注册激活");
            return "login";
        }
        if(user.getPassword().equals(password)){
            session.setAttribute("user",user);
            return "loading";
        }else{
            model.addAttribute("error","密码错误");
            return "login";
        }
    }

    @RequestMapping(path = "/loading",method = RequestMethod.POST)
    public String loading(
            @RequestParam(name = "phoneNumber")
            int phoneNumber,
            @RequestParam(name = "number")
            int number, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            model.addAttribute("error","您已退出系统，请重新登陆");
            return "login";
        }
        if(phoneNumber == 0){
            model.addAttribute("error","该手机号不符合要求，请重新填写");
            return "loading";
        }
        if(number == 0){
            model.addAttribute("error","验证码错误");
            return "loading";
        }
        if(user.getIdentity().equals("厨师")){
            model.addAttribute("user",user);
            return "redirect:/cookBoard";
        }else{
            model.addAttribute("user",user);
            return "redirect:/userBoard";
        }
    }


}
