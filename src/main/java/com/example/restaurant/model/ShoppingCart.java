package com.example.restaurant.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "shopping_cart")
@DynamicUpdate
public class ShoppingCart {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "dishes_id")
    Integer dishesId;
    @Column(name = "count")
    Integer count;
    @Column(name = "customer_id")
    Integer customerId;
    @Column(name = "status")
    Integer status;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
