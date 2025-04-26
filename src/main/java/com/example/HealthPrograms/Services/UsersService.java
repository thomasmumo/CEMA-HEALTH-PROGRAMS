package com.example.HealthPrograms.Services;

import com.example.HealthPrograms.DTOs.LoginDto;
import com.example.HealthPrograms.DTOs.ProgramDto;
import com.example.HealthPrograms.DTOs.UsersDto;
import com.example.HealthPrograms.DTOs.UsersResponseDto;
import com.example.HealthPrograms.Models.HealthPrograms;
import com.example.HealthPrograms.Models.Users;
import com.example.HealthPrograms.Repo.HealthProgramsRepo;
import com.example.HealthPrograms.Repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.util.*;

@Service
public class UsersService {
    private final AuthenticationManager authenticationManager;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private HealthProgramsRepo healthProgramsRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private JwtService jwtService;

    public UsersService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<?> login(LoginDto user) {
        Users userr = userRepo.findByUserName(user.username());
        if (userr == null) {return  new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);}

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.username(),user.password()));

            if (authentication.isAuthenticated()) {



                    Map<String, String> response = new HashMap<>();
                    response.put("token", jwtService.generateToken(user.username()));
                    return ResponseEntity.ok(response);


            }
        }catch (AuthenticationException exception){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Email or password");}
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");

    }

    public ResponseEntity<?> createUser(UsersDto dto) throws IOException {
        if(userRepo.findByUserName(dto.userName()) != null) {
            return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
        }

        var user = new Users();
        user.setName(dto.name());
        user.setUserName(dto.userName());
        user.setEmail(dto.email());
        user.setPassword(encoder.encode(dto.password()));
        user.setRole(dto.role());
        userRepo.save(user);
        addProgram(dto.userName(),dto.programName() );
        Map<String, String> response = new HashMap<>();
        response.put("message", "User created");

        return ResponseEntity.ok(response);

    }

    private void addProgram(String patientUsername, String programName) {
        if (programName != null) {
            Users patient = userRepo.findByUserName(patientUsername);
            if (patient.getPrograms() == null) {
                patient.setPrograms(new ArrayList<>());
            }


            String[] nameArray = programName.split(",");


            for (String name : nameArray) {

                HealthPrograms program = healthProgramsRepo.findByProgramName(name.trim());

                if (!patient.getPrograms().contains(program)) {
                    patient.getPrograms().add(program);
                    program.getUsers().add(patient);

                    userRepo.save(patient);
                    healthProgramsRepo.save(program);
                }
            }

        }





    }


    public List<UsersResponseDto> getAllUsers() {
        return userRepo.findAll().stream()
                .map(user -> new UsersResponseDto(
                        user.getName(),
                        user.getUserName(),
                        user.getEmail(),
                        user.getRole(),
                        user.getPrograms()
                ))
                .toList();
    }

    public UsersResponseDto getUser(String username) {
        Users user = userRepo.findByUserName(username);
        UsersResponseDto response = new UsersResponseDto(
                user.getName(),
                user.getUserName(),
                user.getEmail(),
                user.getRole(),
                user.getPrograms()
        );
        return response;
    }
}
