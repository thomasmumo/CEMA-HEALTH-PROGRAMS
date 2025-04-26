package com.example.HealthPrograms.DTOs;

public record UsersDto(
        String name,
        String userName,
        String email,
        String role,
        String password,
        String programName
) {
}
