package com.SoftwareEngineering.AcademicAdmin.controller;



import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.SoftwareEngineering.AcademicAdmin.dto.request.LoginReqDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.request.SignUpReqDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.LoginResDTO;
import com.SoftwareEngineering.AcademicAdmin.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;

	@PostMapping("/signUp")
	@ResponseStatus(HttpStatus.CREATED)
	public void singUp(@RequestBody @Valid SignUpReqDTO signUpReqDTO){
		userService.signUp(signUpReqDTO);
	}

	@PostMapping("/login")
	public LoginResDTO login(@RequestBody @Valid LoginReqDTO loginReqDTO){
		return userService.login(loginReqDTO);
	}
}
