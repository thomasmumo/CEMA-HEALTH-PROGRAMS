package com.example.HealthPrograms.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Users {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Column(unique = true)
    private String userName;
    private String role;
    private String email;
    private String phone;
    private String password;

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private List<HealthPrograms> programs =new ArrayList<>();

    public Users() {
    }

    public Users(Integer id, String name, String userName, String email, String phone, String role, String password) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<HealthPrograms> getPrograms() {
        return programs;
    }

    public void setPrograms(List<HealthPrograms> programs) {
        this.programs = programs;
    }
}
