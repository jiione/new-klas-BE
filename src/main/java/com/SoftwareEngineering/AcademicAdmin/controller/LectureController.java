package com.SoftwareEngineering.AcademicAdmin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.AssignmentDetailDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.AssignmentResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.DataResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.LectureResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.NoticeResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.PostResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.ProfessorDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.registration.SearchResDTO;
import com.SoftwareEngineering.AcademicAdmin.service.LectureService;
import com.SoftwareEngineering.AcademicAdmin.service.RegistrationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LectureController {
	private final LectureService lectureService;
	private final RegistrationService registrationService;

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

	@GetMapping("/professor/{classId}")
	public ProfessorDTO getProfessor(@PathVariable("classId")Long classId){
		return lectureService.getProfessor(classId);
	}

	@GetMapping("/notice/{postId}")
	public PostResDTO getNoticeDetail(@PathVariable("postId")Long postId){
		return lectureService.getNoticeDetail(postId);
	}

	@GetMapping("/assignment/detail/{postId}/{studentId}")
	public AssignmentDetailDTO getAssignmentDetail(@PathVariable("postId")Long postId,
		@PathVariable("studentId")Long studentId){
		return lectureService.getAssignmentDetail(postId, studentId);
	}

	@GetMapping("/data/{postId}")
	public DataResDTO getDataDetail(@PathVariable("postId")Long postId){
		return lectureService.getDataDetail(postId);
	}

	@GetMapping("/planning/{studentId}")
	public SearchResDTO getPlanning(@PathVariable("studentId")Long studentId,
		@RequestParam("year") Long year,
		@RequestParam("semester") Long semester,
		@RequestParam("search")String search){
		return registrationService.getSearchSubject(studentId, year, semester, search);
	}

	@GetMapping("/planning/{studentId}/{classId}")
	public PostResDTO getPlanningDetail(@PathVariable("studentId")Long studentId,
		@PathVariable("classId")Long classId){
		return lectureService.getPlanning(studentId, classId);
	}
}
