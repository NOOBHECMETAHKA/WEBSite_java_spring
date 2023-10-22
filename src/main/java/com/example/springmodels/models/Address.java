package com.example.springmodels.models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;


@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String city;
    @NotNull
    private String street;
    @NotNull
    private String house;
    @NotNull
    private String entrance;
    @NotNull
    private String apartment;

    @ManyToOne
    @JoinColumn(name="address_person_id", nullable=false)
    private ModelUser modelUser;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_orders_id")
    private List<Order> orders;

    public Address() {
    }

    public Address(String city, String street, String house, String entrance, String apartment, ModelUser application) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.entrance = entrance;
        this.apartment = apartment;
        this.modelUser = application;
    }

    public Address(String city, String street, String house, String entrance, String apartment, ModelUser application, List<Order> orders) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.entrance = entrance;
        this.apartment = apartment;
        this.modelUser = application;
        this.orders = orders;
    }

    public Address(int id, String city, String street, String house, String entrance, String apartment, ModelUser application) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.house = house;
        this.entrance = entrance;
        this.apartment = apartment;
        this.modelUser = application;
    }

    public Address(int id, String city, String street, String house, String entrance, String apartment, ModelUser modelUser, List<Order> orders) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.house = house;
        this.entrance = entrance;
        this.apartment = apartment;
        this.modelUser = modelUser;
        this.orders = orders;
    }

    public Address(int id, String city, String street, String house, String entrance, String apartment) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.house = house;
        this.entrance = entrance;
        this.apartment = apartment;
    }

    public Address(String city, String street, String house, String entrance, String apartment) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.entrance = entrance;
        this.apartment = apartment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public ModelUser getModelUser() {
        return modelUser;
    }

    public void setModelUser(ModelUser modelUser) {
        this.modelUser = modelUser;
    }
}
