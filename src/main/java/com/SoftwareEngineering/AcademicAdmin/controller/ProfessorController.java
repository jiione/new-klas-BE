package com.SoftwareEngineering.AcademicAdmin.controller;

import com.SoftwareEngineering.AcademicAdmin.dto.request.PostReqDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.request.SyllabusReqDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.SyllabusResDTO;
import com.SoftwareEngineering.AcademicAdmin.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProfessorController {
    private final ProfessorService professorService;

    @PostMapping("/syllabus/write")
    public ResponseEntity<Long> writeSyllabus(@RequestBody SyllabusReqDTO syllabusReqDTO){
        return ResponseEntity.ok().body(professorService.writeSyllabus(syllabusReqDTO));
    }

    @GetMapping("/syllabus/read")
    public ResponseEntity<SyllabusResDTO> getSyllabus(@RequestParam Long subjectId){
        return ResponseEntity.ok().body(professorService.getSyllabus(subjectId));
    }

    @PostMapping("/write/post")
    public ResponseEntity<Long> writePost(@RequestBody PostReqDTO postReqDTO){
        return ResponseEntity.ok().body(professorService.writePost(postReqDTO));
    }
}
