package com.example.restaurant.service;

import com.example.restaurant.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    User newUser(String cellphone);
    User addUser(String name,String password,String cellphone,String identity) throws Exception;
    User resetPassword(String cellphone,String code,String password) throws Exception;
    User getUserById(int id);
    User getUserByName(String name);
    Page<User> getUserList(Pageable pageable);
    Page<User> getUserListByIdentity(String identity,Pageable pageable);
    User deleteUser(int userId);

    User getUserByPhone(String phone);
}
