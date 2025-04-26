package com.example.HealthPrograms.DTOs;

import com.example.HealthPrograms.Models.HealthPrograms;

import java.util.List;

public record UsersResponseDto(
        String name,
        String username,
        String email,
        List<HealthPrograms> programs
) {
}
