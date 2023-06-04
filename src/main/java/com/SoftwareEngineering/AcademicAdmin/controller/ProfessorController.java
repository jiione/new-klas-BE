package com.SoftwareEngineering.AcademicAdmin.controller;

import com.SoftwareEngineering.AcademicAdmin.dto.request.SyllabusReqDTO;
import com.SoftwareEngineering.AcademicAdmin.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/professor")
public class ProfessorController {
    private final ProfessorService professorService;

    @PostMapping("/syllabus/write")
    public ResponseEntity<Long> writeSyllabus(@RequestBody SyllabusReqDTO syllabusReqDTO){
        return ResponseEntity.ok().body(professorService.writeSyllabus(syllabusReqDTO));
    }
}
