package com.SoftwareEngineering.AcademicAdmin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SoftwareEngineering.AcademicAdmin.dto.request.SignUpReqDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.Department;
import com.SoftwareEngineering.AcademicAdmin.entity.User;
import com.SoftwareEngineering.AcademicAdmin.exception.department.DepartmentNotFound;
import com.SoftwareEngineering.AcademicAdmin.repository.DepartmentRepository;
import com.SoftwareEngineering.AcademicAdmin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
	private final UserRepository userRepository;
	private final DepartmentRepository departmentRepository;

	public void signUp(SignUpReqDTO signUpReqDTO){
		Department department = getDepartmentByName(signUpReqDTO.getDepartmentName());
		User user = User.of(signUpReqDTO, department);
		userRepository.save(user);
	}

	private Department getDepartmentByName(String name){
		return departmentRepository.findDepartmentByName(name)
			.orElseThrow(DepartmentNotFound::new);
	}

}
