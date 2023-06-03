package com.SoftwareEngineering.AcademicAdmin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SoftwareEngineering.AcademicAdmin.dto.response.registration.RegistrationResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.registration.SearchResDTO;
import com.SoftwareEngineering.AcademicAdmin.service.RegistrationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegistrationController {
	private final RegistrationService registrationService;

	@GetMapping("/search/{studentId}")
	public SearchResDTO getSearchSubject(@PathVariable("studentId")Long studentId,
		@RequestParam("year") Long year,
		@RequestParam("semester") Long semester,
		@RequestParam("search")String search) {
		return registrationService.getSearchSubject(studentId, year, semester, search);
	}
}
