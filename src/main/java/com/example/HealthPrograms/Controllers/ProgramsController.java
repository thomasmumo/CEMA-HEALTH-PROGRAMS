package com.example.HealthPrograms.Controllers;

import com.example.HealthPrograms.DTOs.ProgramDto;
import com.example.HealthPrograms.Services.ProgramsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class ProgramsController {
    @Autowired
    private ProgramsService programsService;
    @PostMapping("/programs/create-program")
    public ResponseEntity<?> createProgram(@RequestBody ProgramDto dto) throws IOException {

        return programsService.createProgram(dto);
    }
    @GetMapping("/programs/all")
    public List<ProgramDto> all() throws IOException {

        return programsService.getAllPrograms();
    }
}
