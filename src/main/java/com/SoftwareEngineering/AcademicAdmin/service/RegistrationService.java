package com.SoftwareEngineering.AcademicAdmin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SoftwareEngineering.AcademicAdmin.dto.response.registration.SearchResDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;
import com.SoftwareEngineering.AcademicAdmin.repository.SubjectsRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class RegistrationService {
	private final LectureService lectureService;
	private final SubjectsRepository subjectsRepository;

	public SearchResDTO getSearchSubject(Long studentId, Long year, Long semester ,String search){
		lectureService.validateUser(studentId);
		List<Subjects> subjects = subjectsRepository.findSubjectsByYearAndSemesterAndSearch(year, semester, search);
		for (Subjects subject : subjects){

		}
		return null;
	}
}
