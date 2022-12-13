package com.example.restaurant.service.impl;

import com.example.restaurant.dao.IUserDao;
import com.example.restaurant.model.User;
import com.example.restaurant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserDao userDao;

    @Override
    public User newUser(String name, String password, String identity, double wage,String phoneNumber) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setIdentity(identity);
        user.setWage(wage);
        user.setIsDelete(0);
        user.setPhoneNumber(phoneNumber);
        userDao.save(user);
        return user;
    }

    @Override
    public User getUserById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public Page<User> getUserList(Pageable pageable) {
        return userDao.findByIsDelete(0,pageable);
    }

    @Override
    public Page<User> getUserListByIdentity(String identity, Pageable pageable) {
        return userDao.findByIdentity(identity,pageable);
    }

    @Override
    public User deleteUser(int userId) {
        User user = userDao.findById(userId);
        user.setIsDelete(0);
        userDao.save(user);
        return user;
    }

    @Override
    public User changePassword(String name, String password) {
        User user = userDao.findByName(name);
        user.setPassword(password);
        userDao.save(user);
        return user;
    }
}
