package com.example.restaurant.service;

import com.example.restaurant.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    User newUser(String name,String password,String identity,double wage,String phoneNumber);
    User getUserById(int id);
    User getUserByName(String name);
    Page<User> getUserList(Pageable pageable);
    Page<User> getUserListByIdentity(String identity,Pageable pageable);
    User deleteUser(int userId);
    User changePassword(String name,String password);
}
