package com.example.restaurant.dao;

import com.example.restaurant.model.Table;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITableDao extends JpaRepository<Table,Integer> {
        Table findById(int id);
        Page<Table> findByIsDelete(int isDelete, Pageable pageable);
}
