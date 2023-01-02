package com.example.restaurant.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "view_order_item_cook")
@DynamicUpdate
public class OrderItemForCook {
    @Id
    @Column(name = "order_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "dishes_id")
    Integer dishesId;
    @Column(name = "dishes_name")
    String dishesName;
    @Column(name = "category_name")
    String categoryName;
    @Column(name = "count")
    Integer count;
    @Column(name = "status")
    String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDishesId() {
        return dishesId;
    }

    public void setDishesId(Integer dishesId) {
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
