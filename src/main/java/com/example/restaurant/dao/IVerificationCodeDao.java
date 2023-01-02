package com.example.restaurant.dao;

import com.example.restaurant.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVerificationCodeDao extends JpaRepository<VerificationCode,Integer> {

    List<VerificationCode> findByCellphoneAndStatusOrderBySendTimeDesc(String cellphone, int status);
}
