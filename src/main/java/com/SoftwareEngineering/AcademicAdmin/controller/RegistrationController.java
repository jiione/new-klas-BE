package com.SoftwareEngineering.AcademicAdmin.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SoftwareEngineering.AcademicAdmin.dto.request.BasketReqDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.request.RegisterReqDTO;
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

	@PostMapping("/register/basket")
	public void registerBasket(@RequestBody @Valid BasketReqDTO basketReqDTO){
		registrationService.registerBasket(basketReqDTO.getStudentId(), basketReqDTO.getClassId());
	}

	@GetMapping("/basket/{studentId}")
	public SearchResDTO getBasket(@PathVariable("studentId")Long studentId){
		return registrationService.getBasket(studentId);
	}

	@PostMapping("/register/subject")
	public void registerSubject(@RequestBody @Valid RegisterReqDTO reqDTO){
		registrationService.registerSubject(reqDTO.getStudentId(), reqDTO.getClassId(), reqDTO.getYear(), reqDTO.getSemester());
	}

}
