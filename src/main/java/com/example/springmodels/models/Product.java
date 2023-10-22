package com.example.springmodels.models;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.List;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private double price;
    @Column
    private String description;


    @ManyToMany
    @JoinTable(name = "order_list", joinColumns =@JoinColumn(name = "product_id"),
    inverseJoinColumns =@JoinColumn(name = "order_id"))
    private List<Order> orders;

    public Product() {
    }

    public Product(String name, double price, String description, List<Order> orders) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.orders = orders;
    }

    public Product(int id, String name, double price, String description, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
