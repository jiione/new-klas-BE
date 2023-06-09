package com.SoftwareEngineering.AcademicAdmin.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SoftwareEngineering.AcademicAdmin.dto.response.AdminResDTO;
import com.SoftwareEngineering.AcademicAdmin.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
	private final AdminService adminService;

	@GetMapping("/user/{adminId}")
	public AdminResDTO getUsers(@PathVariable("adminId")Long adminId){
		return adminService.getUsers(adminId);
	}

	@DeleteMapping("/user/{adminId}/{studentId}")
	public void deleteUser(@PathVariable("adminId")Long adminId,
		@PathVariable("studentId")Long studentId){
		adminService.deleteUser(adminId, studentId);
	}
}
