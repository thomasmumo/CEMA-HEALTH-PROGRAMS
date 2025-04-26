package com.example.HealthPrograms.Repo;



import com.example.HealthPrograms.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByUserName(String username);

    List<Users> findAllByRole(String role);

}

