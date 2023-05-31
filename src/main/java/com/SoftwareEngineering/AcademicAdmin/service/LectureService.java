package com.SoftwareEngineering.AcademicAdmin.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SoftwareEngineering.AcademicAdmin.dto.response.LectureDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.LectureResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.SubjectDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.Course;
import com.SoftwareEngineering.AcademicAdmin.entity.Semester;
import com.SoftwareEngineering.AcademicAdmin.entity.User;
import com.SoftwareEngineering.AcademicAdmin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class LectureService {
	private final UserRepository userRepository;

	public LectureResDTO getLecture(Long studentId){
		User user = getUser(studentId);
		return getLectureList(user.getSemesters());
	}

	public User getUser(Long studentId){
		return userRepository.getUserByStudentId(studentId);
	}

	private Set<SubjectDTO> getSubjectInfo(Set<Course> courses) {
		return courses.stream()
			.map(course -> SubjectDTO.from(course.getSubjects()))
			.collect(Collectors.toSet());
	}

	private LectureResDTO getLectureList(Set<Semester> semesters) {
		Set<LectureDTO> lectureDTOS = semesters.stream()
			.map(semester -> LectureDTO.from(semester, getSubjectInfo(semester.getCourses())))
			.collect(Collectors.toSet());

		return LectureResDTO.from(lectureDTOS);
	}

}
