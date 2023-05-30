package com.SoftwareEngineering.AcademicAdmin.controller;


import com.SoftwareEngineering.AcademicAdmin.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/grade")
public class GradeController {

    private final GradeService gradeService;

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getGrade(@RequestParam Long studentId){
        return ResponseEntity.ok().body(gradeService.getGrade(studentId));
    }

}
