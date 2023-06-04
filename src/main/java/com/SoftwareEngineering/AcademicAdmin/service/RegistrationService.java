package com.SoftwareEngineering.AcademicAdmin.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SoftwareEngineering.AcademicAdmin.dto.response.ScheduleDetailDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.registration.SearchDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.registration.SearchResDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;
import com.SoftwareEngineering.AcademicAdmin.repository.SubjectsRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class RegistrationService {
	private final LectureService lectureService;
	private final StudentMainService studentMainService;
	private final SubjectsRepository subjectsRepository;

	public SearchResDTO getSearchSubject(Long studentId, Long year, Long semester ,String search){
		lectureService.validateUser(studentId);
		List<Subjects> subjects = subjectsRepository.findSubjectsByYearAndSemesterAndSearch(year, semester, search);
		return getSearchResponse(subjects);
	}

	private SearchResDTO getSearchResponse(List<Subjects> subjects){
		List<SearchDTO> searchDTOS = subjects.stream()
			.map(subject -> {
				List<ScheduleDetailDTO> scheduleDetailDTOS = studentMainService.getScheduleDetail(subject);
				return SearchDTO.of(subject, scheduleDetailDTOS);
			})
			.collect(Collectors.toList());

		return SearchResDTO.from(searchDTOS);
	}
}
