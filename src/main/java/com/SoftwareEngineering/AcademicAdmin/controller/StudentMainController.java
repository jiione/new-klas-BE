package com.SoftwareEngineering.AcademicAdmin.controller;

import com.SoftwareEngineering.AcademicAdmin.dto.response.PostResDto;
import com.SoftwareEngineering.AcademicAdmin.dto.response.ScheduleResDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.Post;
import com.SoftwareEngineering.AcademicAdmin.service.StudentMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class StudentMainController {

    private final StudentMainService studentMainService;

    @GetMapping("/notice")
    public ResponseEntity<List<PostResDto>> getRecentNotice(@RequestParam Long studentId){
        return ResponseEntity.ok().body(studentMainService.getTop5Post(studentId));
    }

    @GetMapping("/semesters")
    public ResponseEntity<List<String>> getSemesters(@RequestParam Long studentId){
        return ResponseEntity.ok().body(studentMainService.getUserSemesters(studentId));
    }

    @GetMapping("/schedule")
    public ResponseEntity<List<ScheduleResDTO>> getSchedule(@RequestParam Long studentId, @RequestParam Long semester, @RequestParam Long year){
        return ResponseEntity.ok().body(studentMainService.getStudentSchedule(studentId,year,semester));
    }
}
