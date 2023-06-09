package com.SoftwareEngineering.AcademicAdmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SoftwareEngineering.AcademicAdmin.dto.response.AdminResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.UserDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.User;
import com.SoftwareEngineering.AcademicAdmin.exception.user.AdminNotDelete;
import com.SoftwareEngineering.AcademicAdmin.exception.user.NotAdmin;
import com.SoftwareEngineering.AcademicAdmin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class AdminService {
	private final UserRepository userRepository;
	private final LectureService lectureService;

	public AdminResDTO getUsers(Long adminId){
		validateAdmin(adminId);
		List<User> users = userRepository.getUsers(adminId);
		List<UserDTO> userDTOS = new ArrayList<>();
		for (User user : users){
			UserDTO userDTO = UserDTO.from(user);
			userDTOS.add(userDTO);
		}
		return AdminResDTO.from(userDTOS);
	}

	public void deleteUser(Long adminId, Long studentId){
		validateAdmin(adminId);
		lectureService.validateUser(studentId);
		validateDeleteId(studentId);
		userRepository.deleteByStudentId(studentId);
	}


	private void validateDeleteId(Long studentId){
		if (studentId.equals(1111L))
			throw new AdminNotDelete();
	}

	private void validateAdmin(Long adminId){
		if (!adminId.equals(1111L))
			throw new NotAdmin();
	}
}
