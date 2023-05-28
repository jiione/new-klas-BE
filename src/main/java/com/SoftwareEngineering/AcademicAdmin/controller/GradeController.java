package com.SoftwareEngineering.AcademicAdmin.controller;


import com.SoftwareEngineering.AcademicAdmin.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/grade")
public class GradeController {

    private final GradeService gradeService;


}
