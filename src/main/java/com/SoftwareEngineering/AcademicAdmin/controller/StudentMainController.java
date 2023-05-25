package com.SoftwareEngineering.AcademicAdmin.controller;

import com.SoftwareEngineering.AcademicAdmin.dto.response.PostResDto;
import com.SoftwareEngineering.AcademicAdmin.entity.Post;
import com.SoftwareEngineering.AcademicAdmin.service.StudentMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class StudentMainController {

    StudentMainService studentMainService;

    @GetMapping("/notice")
    public ResponseEntity<List<PostResDto>> getRecentNotice(){
        return ResponseEntity.ok().body(studentMainService.getTop5Post(0));
    }
}
