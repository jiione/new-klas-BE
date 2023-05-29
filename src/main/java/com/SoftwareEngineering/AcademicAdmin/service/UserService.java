package com.SoftwareEngineering.AcademicAdmin.service;

import com.SoftwareEngineering.AcademicAdmin.entity.Course;
import com.SoftwareEngineering.AcademicAdmin.entity.Semester;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SoftwareEngineering.AcademicAdmin.dto.request.LoginReqDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.request.SignUpReqDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.LoginResDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.Department;
import com.SoftwareEngineering.AcademicAdmin.entity.User;
import com.SoftwareEngineering.AcademicAdmin.exception.department.DepartmentNotFound;
import com.SoftwareEngineering.AcademicAdmin.exception.user.InvalidPassword;
import com.SoftwareEngineering.AcademicAdmin.exception.user.UserNotFound;
import com.SoftwareEngineering.AcademicAdmin.repository.DepartmentRepository;
import com.SoftwareEngineering.AcademicAdmin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
	private final UserRepository userRepository;
	private final DepartmentRepository departmentRepository;

	public LoginResDTO login(LoginReqDTO loginReqDTO){
		User user = getUser(loginReqDTO.getStudentId());
		validateUser(user, loginReqDTO.getPassword());
		return LoginResDTO.from(user.getId());
	}

	private void validateUser (User user, String password){
		if(!user.getPassword().equals(password))
			throw new InvalidPassword();
	}
	public User getUser(Long studentId){
		return userRepository.findUserByStudentId(studentId)
			.orElseThrow(UserNotFound::new);
	}

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
