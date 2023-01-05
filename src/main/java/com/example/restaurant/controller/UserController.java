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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping(path= "/login",method = RequestMethod.POST)
    public String login(
            @RequestParam(name = "name")
            String name,
            @RequestParam(name = "password")
            String password, Model model, HttpServletRequest request){
        User user = userService.getUserByName(name);
        if(user == null){
            model.addAttribute("error","账号不存在");
            return "login";
        }
        if(password.equals(user.getPassword())){
            if(("厨师").equals(user.getIdentity())){
                request.getSession().setAttribute("user",user);
                return "redirect:/cookBoard";
            }else{
                request.getSession().setAttribute("user",user);
                return "redirect:/userBoard";
            }
        }else{
            model.addAttribute("error","密码错误");
            return "login";
        }
    }

    @RequestMapping(path = "/userLisePage",method = RequestMethod.POST)
    public String userListPage(
            @RequestParam(name = "number")
            int n,Model model){
        PageRequest of = PageRequest.of(n, 10);
        Page<User> userList = userService.getUserList(of);
        model.addAttribute("userList",userList);
        return "users/userList";
    }

    @RequestMapping(path = "/changePassword",method = RequestMethod.POST)
    public String changePassword(
            @RequestParam(name = "code")
            String code,
            @RequestParam(name = "password")
            String password,Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        try {
            userService.resetPassword(user.getPhone(),code,password);
        } catch (Exception e) {
            model.addAttribute("tip",e.getMessage());
            return "users/changeUserInformation";
        }
        model.addAttribute("tip","更改成功");
        return "users/changeUserInformation";
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
            String cellphone,Model model){
        try {
            userService.addUser(name,password,cellphone,identity);
            model.addAttribute("tip","添加成功");
            return "users/newUser";
        } catch (Exception e) {
            model.addAttribute("tip",e.getMessage());
            return "users/newUser";
        }
    }

    @RequestMapping(path = "/deleteUser",method = RequestMethod.POST)
    public String deleteUser(
            @RequestParam(name = "id")
            int id,Model model){
        userService.deleteUser(id);
        model.addAttribute("tip","删除成功");
        return "users/deleteUser";
    }
}
