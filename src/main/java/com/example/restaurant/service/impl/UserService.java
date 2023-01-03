package com.example.restaurant.service.impl;

import com.example.restaurant.dao.IUserDao;
import com.example.restaurant.dao.IVerificationCodeDao;
import com.example.restaurant.model.User;
import com.example.restaurant.model.VerificationCode;
import com.example.restaurant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserDao userDao;
    @Autowired
    IVerificationCodeDao verificationCodeDao;



    @Override
    public User addUser(String name, String password, String cellphone, String identity) throws Exception {
        User user = userDao.findByPhone(cellphone);
        if(user == null){
            User user1 = new User();
            user1.setName(name);
            user1.setPassword(password);
            user1.setStatus(0);
            user1.setIdentity(identity);
            user1.setPhone(cellphone);
            userDao.save(user1);
            return user1;
        }else{
            throw new Exception("该手机号已存在，注册失败");
        }
    }

    @Override
    public User resetPassword(String cellphone,String code, String password) throws Exception {
        User user = userDao.findByPhone(cellphone);
        List<VerificationCode> verificationCodeLists =
                verificationCodeDao.findByCellphoneAndStatusOrderBySendTimeDesc(cellphone, 0);
        if(!verificationCodeLists.isEmpty()){
            VerificationCode verificationCode = verificationCodeLists.get(0);
            if(new Date().getTime() - verificationCode.getSendTime().getTime() > 5*60*1000){
                throw new Exception("验证码已过期");
            }
            if(verificationCode.getCode().equals(code)){
                for(VerificationCode verificationCode1:verificationCodeLists){
                    verificationCode1.setStatus(1);
                }
                verificationCodeDao.saveAll(verificationCodeLists);
                user.setPassword(password);
                userDao.save(user);
                return user;
            }else{
                throw new Exception("验证码错误");
            }
        }else{
            throw new Exception("验证码错误");
        }
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
        return userDao.findByStatus(0,pageable);
    }

    @Override
    public Page<User> getUserListByIdentity(String identity, Pageable pageable) {
        return userDao.findByIdentity(identity,pageable);
    }

    @Override
    public User deleteUser(int userId) {
        User user = userDao.findById(userId);
        user.setStatus(1);
        userDao.save(user);
        return user;
    }


    @Override
    public User getUserByPhone(String phone) {
        return userDao.findByPhone(phone);
    }
}
