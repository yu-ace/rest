package com.example.restaurant.service.impl;

import com.example.restaurant.dao.ITableDao;
import com.example.restaurant.model.Table;
import com.example.restaurant.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TableService implements ITableService {

    @Autowired
    ITableDao tableDao;

    @Override
    public Table newTable(String name) {
        Table table = new Table();
        table.setName(name);
        table.setStatus(0);
        tableDao.save(table);
        return table;
    }

    @Override
    public Table reduceTable(int id) {
        Table table = tableDao.findById(id);
        table.setStatus(1);
        tableDao.save(table);
        return table;
    }

    @Override
    public Table getTableById(int tableId) {
        return tableDao.findById(tableId);
    }

    @Override
    public Page<Table> getTableList(int status,Pageable pageable) {
        return tableDao.findByStatus(status,pageable);
    }
}
