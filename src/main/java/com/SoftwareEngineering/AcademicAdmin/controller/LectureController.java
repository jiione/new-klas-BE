package com.SoftwareEngineering.AcademicAdmin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SoftwareEngineering.AcademicAdmin.dto.response.LectureResDTO;
import com.SoftwareEngineering.AcademicAdmin.service.LectureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lecture/{studentId}")
public class LectureController {
	private final LectureService lectureService;

	@GetMapping
	public LectureResDTO getLecture(@PathVariable("studentId") Long studentId) {
		return lectureService.getLecture(studentId);
	}
}
