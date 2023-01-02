package com.example.restaurant.model;

import javax.persistence.*;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;



@Entity
@Table(name = "orders")
@DynamicUpdate
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "customer_id")
    Integer customerId;
    @Column(name = "total")
    Double total;
    @Column(name = "table_id")
    Integer tableId;
    @Column(name = "status")
    String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
