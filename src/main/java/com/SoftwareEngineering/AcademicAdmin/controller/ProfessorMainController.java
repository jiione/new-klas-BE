package com.SoftwareEngineering.AcademicAdmin.controller;

import com.SoftwareEngineering.AcademicAdmin.dto.response.ScheduleResDTO;
import com.SoftwareEngineering.AcademicAdmin.service.ProfessorMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/professor/main")
public class ProfessorMainController {
    private final ProfessorMainService professorMainService;
    @GetMapping("/schedule")
    public ResponseEntity<List<ScheduleResDTO>> getSchedule(@RequestParam Long studentId, @RequestParam String semester){
        return ResponseEntity.ok().body(professorMainService.getProfessorSchedule(studentId,semester));
    }
}
