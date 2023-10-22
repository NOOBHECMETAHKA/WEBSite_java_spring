package com.example.springmodels.models;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.List;

//@Entity
//@Table(name = "person")
public class Person {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Min(6)
//    private String email;
//    @Min(8)
//    private String password;
//    @NotNull
//    private String role;
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "address_person_id")
//    private List<Address> addresses;
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "application_person_id")
//    private List<Application> applications;
//
//    public Person(String email, String password, String role) {
//        this.email = email;
//        this.password = password;
//        this.role = role;
//    }
//
//    public Person(int id, String email, String password, String role) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.role = role;
//    }
//
//    public Person(String email, String password, String role, List<Address> addresses) {
//        this.email = email;
//        this.password = password;
//        this.role = role;
//        this.addresses = addresses;
//    }
//
//    public Person(String email, String password, String role, List<Address> addresses, List<Application> applications) {
//        this.email = email;
//        this.password = password;
//        this.role = role;
//        this.addresses = addresses;
//        this.applications = applications;
//    }
//
//    public Person(int id, String email, String password, String role, List<Address> addresses, List<Application> applications) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.role = role;
//        this.addresses = addresses;
//        this.applications = applications;
//    }
//
//    public Person() {
//
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public List<Address> getAddresses() {
//        return addresses;
//    }
//
//    public void setAddresses(List<Address> addresses) {
//        this.addresses = addresses;
//    }
//
//    public List<Application> getApplications() {
//        return applications;
//    }
//
//    public void setApplications(List<Application> applications) {
//        this.applications = applications;
//    }

}
