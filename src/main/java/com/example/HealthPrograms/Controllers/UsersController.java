package com.example.HealthPrograms.Controllers;

import com.example.HealthPrograms.DTOs.LoginDto;
import com.example.HealthPrograms.DTOs.UsersDto;

import com.example.HealthPrograms.DTOs.UsersResponseDto;
import com.example.HealthPrograms.Services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;
    @PostMapping("/users/user-login")
    public ResponseEntity<?> login(@RequestBody LoginDto user) {
        return usersService.login(user);

    }
    @PostMapping("/users/create-user")
    public ResponseEntity<?> createUser(@RequestBody UsersDto dto) throws IOException {

        return usersService.createUser(dto);
    }

    @GetMapping("/users/all")
    public List<UsersResponseDto> all() throws IOException {

        return usersService.getAllUsers();
    }
    @GetMapping("/users/user/{user-name}")
    public UsersResponseDto all(@PathVariable("user-name") String userName) throws IOException {

        return usersService.getUser(userName);
    }
}
