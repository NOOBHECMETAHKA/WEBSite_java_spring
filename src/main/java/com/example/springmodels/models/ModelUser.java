package com.example.springmodels.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ModelUser {
    public ModelUser(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_User;
    private String username;
    private String password;
    private boolean active;

    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<RoleEnum> roles;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_person_id")
    private Set<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "application_person_id")
    private Set<Application> applications;

    public Long getID_User() {
        return ID_User;
    }

    public void setID_User(Long ID_User) {
        this.ID_User = ID_User;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEnum> roles) {
        this.roles = roles;
    }

    public ModelUser(String username, String password, boolean active, Set<RoleEnum> roles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }
}
