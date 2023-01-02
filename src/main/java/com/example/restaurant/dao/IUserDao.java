package com.example.restaurant.dao;

import com.example.restaurant.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<User,Integer> {
    User findById(int id);
    User findByName(String name);
    Page<User> findByIdentity(String identity, Pageable pageable);
    Page<User> findByStatus(int status,Pageable pageable);
    User findByPhone(String phone);
}
