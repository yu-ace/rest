package com.example.restaurant.service;


import com.example.restaurant.model.User;

public interface IVerificationCodeService {
    void sendVerificationCode(String cellphone) throws Exception;
    User checkVerificationCode(String cellphone, String code) throws Exception;
}
