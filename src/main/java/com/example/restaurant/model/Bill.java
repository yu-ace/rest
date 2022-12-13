package com.example.restaurant.model;

import javax.persistence.*;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;



@Entity
@Table(name = "bill")
@DynamicUpdate
public class Bill {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "customer_id")
    int customerId;
    @Column(name = "total")
    double total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
