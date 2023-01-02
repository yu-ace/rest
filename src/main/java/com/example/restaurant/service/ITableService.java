package com.example.restaurant.service;

import com.example.restaurant.model.Table;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITableService {
    Table newTable(String name);
    Table reduceTable(int id);
    Table getTableById(int tableId);
    Page<Table> getTableList(int status,Pageable pageable);
}
