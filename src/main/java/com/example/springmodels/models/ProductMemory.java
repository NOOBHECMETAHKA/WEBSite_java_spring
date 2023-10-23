package com.example.springmodels.models;

import org.springframework.context.annotation.Bean;

import javax.persistence.Column;

public class ProductMemory {
    private int id;
    private String name;
    private double price;

    public ProductMemory(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public ProductMemory(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductMemory(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
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
}
