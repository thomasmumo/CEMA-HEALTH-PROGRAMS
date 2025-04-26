package com.example.HealthPrograms.Services;

import com.example.HealthPrograms.DTOs.ProgramDto;
import com.example.HealthPrograms.Models.HealthPrograms;
import com.example.HealthPrograms.Repo.HealthProgramsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProgramsService {
    @Autowired
    private HealthProgramsRepo healthProgramsRepo;

    public ResponseEntity<?> createProgram(ProgramDto dto) {
        HealthPrograms program1 = healthProgramsRepo.findByProgramName(dto.name());
        if (program1 != null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "program already exists");
            return ResponseEntity.ok(response);
        }
        HealthPrograms program = new HealthPrograms();
        program.setProgramName(dto.name());
        healthProgramsRepo.save(program);
        return ResponseEntity.ok().build();

    }

    public List<ProgramDto> getAllPrograms() {
        return healthProgramsRepo.findAll().stream()
                .map(program -> new ProgramDto(
                        program.getProgramName()
                ))
                .toList();
    }
}
