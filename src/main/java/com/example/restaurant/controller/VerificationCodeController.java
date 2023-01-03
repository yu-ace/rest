package com.example.restaurant.controller;

import com.example.restaurant.model.Customer;
import com.example.restaurant.model.User;
import com.example.restaurant.service.ICustomerService;
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
    ICustomerService customerService;

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
        return "customer";
    }

    @RequestMapping(path = "/checkVerificationCode",method = RequestMethod.POST)
    public String checkVerificationCode(
            @RequestParam(name = "phone")
            String cellPhone,
            @RequestParam(name = "code")
            String code,
            @RequestParam(name = "tableId")
            int tableId,
            Model model, HttpSession session,HttpSession session1){
        try {
            Customer customer = verificationCodeService.checkVerificationCode(cellPhone, code);
            session.setAttribute("customer",customer);
            session1.setAttribute("tableId",tableId);
            return "redirect:/customerIndex";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
            return "customer";
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
