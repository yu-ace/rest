package com.example.restaurant.service;


import com.example.restaurant.model.Customer;

public interface IVerificationCodeService {
    void sendVerificationCode(String cellphone) throws Exception;
    Customer checkVerificationCode(String cellphone, String code) throws Exception;
}
