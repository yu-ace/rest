package com.example.restaurant.service.impl;

import com.example.restaurant.dao.IVerificationCodeDao;
import com.example.restaurant.model.Customer;
import com.example.restaurant.model.User;
import com.example.restaurant.model.VerificationCode;
import com.example.restaurant.service.ICustomerService;
import com.example.restaurant.service.ISmsService;
import com.example.restaurant.service.IUserService;
import com.example.restaurant.service.IVerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class VerificationCodeService implements IVerificationCodeService {

    @Autowired
    IVerificationCodeDao verificationCodeDao;
    @Autowired
    ISmsService smsService;
    @Autowired
    ICustomerService customerService;

    //定义一个取值范围
    static String a = "abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789012345678901234567890123456789_";
    public static void main(String[] args) {
        Random random = new Random();
        String b = "";
        //生成一个9位的字符串
        for(int i = 0;i < 9;i++){
            b = b + a.charAt(random.nextInt(a.length()-1));
        }
        System.out.println(b);
    }


    @Override
    public void sendVerificationCode(String cellphone) throws Exception {
       List<VerificationCode> verificationCodeList = verificationCodeDao
               .findByCellphoneAndStatusOrderBySendTimeDesc(cellphone,0);
       for(VerificationCode verificationCode : verificationCodeList){
           verificationCode.setStatus(1);
       }
        verificationCodeDao.saveAll(verificationCodeList);

        //从0-9999中随即一个数字
        int num = new Random().nextInt(999999);
        //如果随机的数字小于1000的话，就加上1000,已达到4位数
        if(num < 100000){
            num = num + 100000;
        }
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setCellphone(cellphone);
        verificationCode.setCode(num+"");
        verificationCode.setStatus(0);
        verificationCode.setSendTime(new Date());
        verificationCodeDao.save(verificationCode);
        smsService.sendVerifyCode(cellphone,num+"");
    }

    @Override
    public Customer checkVerificationCode(String cellphone, String code) throws Exception {
        List<VerificationCode> verificationCodeList =
                verificationCodeDao.findByCellphoneAndStatusOrderBySendTimeDesc(cellphone, 0);
        if(!verificationCodeList.isEmpty()){
            VerificationCode verificationCode = verificationCodeList.get(0);
            if(verificationCode.getSendTime().getTime() - (new Date()).getTime() > 5*60*1000){
                throw new Exception("验证码已过期");
            }
            if(verificationCode.getCode().equals(code)){
                for(VerificationCode v : verificationCodeList){
                    v.setStatus(1);
                }
                verificationCodeDao.saveAll(verificationCodeList);
                Customer customer = customerService.getCustomerByPhone(cellphone);
                if(customer == null){
                    Customer customer1 = customerService.newCustomer(cellphone);
                    return customer1;
                }
                return customer;
            }else{
                throw new Exception("验证码错误");
            }
        }else {
            throw new Exception("验证码错误,请输入自己的手机号");
        }

    }
}
