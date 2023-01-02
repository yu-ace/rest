package com.example.restaurant.controller;

import com.example.restaurant.model.User;
import com.example.restaurant.service.IUserService;
import com.example.restaurant.service.IVerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class VerificationCodeController {

    @Autowired
    IVerificationCodeService verificationCodeService;
    @Autowired
    IUserService userService;

    @RequestMapping(path = "sendVerificationCode",method = RequestMethod.POST)
    public String sendVerificationCode(
            @RequestParam(name= "phone")
            String cellPhone , Model model){
        try {
            verificationCodeService.sendVerificationCode(cellPhone);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
        }
        return "login";
    }

    @RequestMapping(path = "/checkVerificationCode",method = RequestMethod.POST)
    public String checkVerificationCode(
            @RequestParam(name = "phone")
            String cellPhone,
            @RequestParam(name = "code")
            String code, Model model, HttpSession session){
        try {
            User user = verificationCodeService.checkVerificationCode(cellPhone, code);
            if(("厨师").equals(user.getIdentity())){
                session.setAttribute("user",user);
                return "redirect:/cookBoard";
            }else{
                session.setAttribute("user",user);
                return "redirect:/userBoard";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
            return "login";
        }
    }

    @ResponseBody
    @RequestMapping(path="send",method = RequestMethod.GET)
    public void sendVerification(String cellphone){
        try {
            verificationCodeService.sendVerificationCode(cellphone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
