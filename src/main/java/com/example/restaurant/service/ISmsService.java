package com.example.restaurant.service;

public interface ISmsService {

    boolean sendVerifyCode(String cellPhone,String code);

}
