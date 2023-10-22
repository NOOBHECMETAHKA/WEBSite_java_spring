package com.example.springmodels.models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private LocalDate dateCreated;

    @ManyToOne
    @JoinColumn(name="status_id", nullable=false)
    private Status status;

    @ManyToOne
    @JoinColumn(name="address_orders_id", nullable=false)
    private Address address;

    @ManyToMany
    @JoinTable(name = "order_list", joinColumns =@JoinColumn(name = "order_id"),
            inverseJoinColumns =@JoinColumn(name = "product_id"))
    private List<Product> products;

    public Order(LocalDate dateCreated, Status status, List<Product> products) {
        this.dateCreated = dateCreated;
        this.status = status;
        this.products = products;
    }

    public Order(int id, LocalDate dateCreated, Status status, List<Product> products) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.status = status;
        this.products = products;
    }

    public Order() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
