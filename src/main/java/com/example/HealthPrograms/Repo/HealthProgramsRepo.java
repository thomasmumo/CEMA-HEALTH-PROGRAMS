package com.example.HealthPrograms.Repo;

import com.example.HealthPrograms.Models.HealthPrograms;
import com.example.HealthPrograms.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthProgramsRepo extends JpaRepository<HealthPrograms, Integer> {

    HealthPrograms findByProgramName(String name);

}
