package com.SoftwareEngineering.AcademicAdmin.controller;

import com.SoftwareEngineering.AcademicAdmin.dto.request.GradeReqDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.request.PostReqDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.request.PostUpdateReqDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.request.SyllabusReqDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.CourseListResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.PostDetailResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.SyllabusResDTO;
import com.SoftwareEngineering.AcademicAdmin.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PutMapping("/update/post")
    public ResponseEntity<Long> updatePost(@RequestBody PostUpdateReqDTO postUpdateReqDTO){
        return ResponseEntity.ok().body(professorService.updatePost(postUpdateReqDTO));
    }

    @DeleteMapping("/delete/post")
    public void deletePost(@RequestParam Long postId){
        professorService.deletePost(postId);
    }

    @PostMapping("/give/grade")
    public ResponseEntity<Long> giveGrade(@RequestBody GradeReqDTO gradeReqDTO){
        return ResponseEntity.ok().body(professorService.giveGrade(gradeReqDTO));
    }

    @GetMapping("/professor/read/assignment")
    public ResponseEntity<PostDetailResDTO> readAssignment(@RequestParam Long postId){
        return ResponseEntity.ok().body(professorService.readAssignment(postId));
    }

    @GetMapping("/course/list")
    public ResponseEntity<List<CourseListResDTO>> getCourseStudentList(@RequestParam Long classId){
        return ResponseEntity.ok().body(professorService.getCourseStudentList(classId));
    }
}
