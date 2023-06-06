package com.SoftwareEngineering.AcademicAdmin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.AssignmentResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.LectureResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.NoticeResDTO;
import com.SoftwareEngineering.AcademicAdmin.service.LectureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LectureController {
	private final LectureService lectureService;

	@GetMapping("/lecture/{studentId}")
	public LectureResDTO getLecture(@PathVariable("studentId") Long studentId) {
		return lectureService.getLecture(studentId);
	}

	@GetMapping("/notice/{studentId}/{classId}")
	public NoticeResDTO getNotice(@PathVariable("studentId")Long studentId,
		@PathVariable("classId")Long classId){
		return lectureService.getNotice(studentId, classId);
	}

	@GetMapping("/assignment/{studentId}/{classId}")
	public AssignmentResDTO getAssignment(@PathVariable("studentId")Long studentId,
		@PathVariable("classId")Long classId){
		return lectureService.getAssignment(studentId, classId);
	}
}
