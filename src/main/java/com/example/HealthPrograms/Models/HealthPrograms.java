package com.example.HealthPrograms.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class HealthPrograms {
    @Id
    @GeneratedValue
    private Integer id;
    private String programName;
    @ManyToMany
    @JoinTable(
            name = "programs_hospitals",
            joinColumns={
                    @JoinColumn(name = "programs_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id")
            }
    )
    @JsonIgnore
    private List<Users> users =new ArrayList<>();

    public HealthPrograms() {
    }



    public HealthPrograms(Integer id, String programName,List<Users> users) {
        this.id = id;
        this.programName = programName;
        this.users = users;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
