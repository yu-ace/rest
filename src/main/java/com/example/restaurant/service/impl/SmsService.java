package com.example.restaurant.service.impl;

import com.example.restaurant.dao.IVerificationCodeDao;
import com.example.restaurant.model.VerificationCode;
import com.example.restaurant.service.ISmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SmsService implements ISmsService {

    @Autowired
    IVerificationCodeDao cellPhoneDao;

    @Override
    public boolean sendVerifyCode(String cellPhone, String code) {
        System.out.println(code);
        return true;
    }



}
