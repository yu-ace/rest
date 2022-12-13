package com.example.restaurant.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "view_order_list_cook")
@DynamicUpdate
public class OrderListCook {
    @Id
    @Column(name = "order_list_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "dishes_id")
    int dishesId;
    @Column(name = "dishes_name")
    String dishesName;
    @Column(name = "category_name")
    String categoryName;
    @Column(name = "count")
    int count;
    @Column(name = "status")
    String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDishesId() {
        return dishesId;
    }

    public void setDishesId(int dishesId) {
        this.dishesId = dishesId;
    }

    public String getDishesName() {
        return dishesName;
    }

    public void setDishesName(String dishesName) {
        this.dishesName = dishesName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
