package com.SoftwareEngineering.AcademicAdmin.service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SoftwareEngineering.AcademicAdmin.dto.response.ScheduleDetailDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.registration.SearchDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.registration.SearchResDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.Basket;
import com.SoftwareEngineering.AcademicAdmin.entity.Course;
import com.SoftwareEngineering.AcademicAdmin.entity.Semester;
import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;
import com.SoftwareEngineering.AcademicAdmin.entity.User;
import com.SoftwareEngineering.AcademicAdmin.exception.register.AlreadyRegisterException;
import com.SoftwareEngineering.AcademicAdmin.exception.register.AlreadyTimeException;
import com.SoftwareEngineering.AcademicAdmin.exception.register.ClosedRegisterException;
import com.SoftwareEngineering.AcademicAdmin.exception.register.CreditExceedException;
import com.SoftwareEngineering.AcademicAdmin.exception.semester.SemesterNotFound;
import com.SoftwareEngineering.AcademicAdmin.exception.subject.SubjectNotFound;

import com.SoftwareEngineering.AcademicAdmin.repository.BasketRepository;
import com.SoftwareEngineering.AcademicAdmin.repository.CourseRepository;
import com.SoftwareEngineering.AcademicAdmin.repository.SemesterRepository;
import com.SoftwareEngineering.AcademicAdmin.repository.SubjectsRepository;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class RegistrationService {
	private final UserService userService;
	private final LectureService lectureService;
	private final StudentMainService studentMainService;

	private final CourseRepository courseRepository;
	private final BasketRepository basketRepository;
	private final SubjectsRepository subjectsRepository;
	private final SemesterRepository semesterRepository;


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

	public void registerBasket(Long studentId, Long classId){
		User user = userService.getUser(studentId);
		Subjects subjects = getSubject(classId);
		Basket basket = Basket.of(user, subjects);
		basketRepository.save(basket);
	}

	public Subjects getSubject(Long classId){
		return subjectsRepository.findById(classId)
			.orElseThrow(SubjectNotFound::new);
	}

	public SearchResDTO getBasket(Long studentId){
		lectureService.validateUser(studentId);
		List<Basket> baskets = basketRepository.findBasketByStudentId(studentId);

		List<Subjects> subjects = new ArrayList<>();
		for (Basket basket : baskets){
			subjects.add(basket.getSubjects());
		}
		return getSearchResponse(subjects);
	}

	public void registerSubject (Long studentId, Long classId, Long year, Long s){

		validateSameCourse(studentId, classId, year, s);
		Semester semester = semesterRepository.findSemesterByUserAndYearAndSemester(studentId, year, s);

		Subjects subjects = getSubject(classId);
		validateSubject(subjects);

		List<Course> courses = courseRepository.getCoursesByStudentId(studentId,year,s);
		validateCredit(courses, subjects);
		validateSameTime(courses, subjects);

		Course course = Course.of(semester, subjects);
		courseRepository.save(course);
		subjects.updatePersonnel();
	}

	private List<Integer> getDays(Subjects subjects){
		return subjects.getDay().chars()
			.mapToObj(Character::getNumericValue)
			.collect(Collectors.toList());
	}

	private void validateSameTime(List<Course> courses, Subjects subjects){
		int [][] arr = new int[8][5];

		for (Course c : courses){
			List<Integer> days1 = getDays(c.getSubjects());
			String [] times1 = c.getSubjects().getTime().split(",");
			for(int i =0; i < days1.size();i++){
				for (char ch : times1[i].toCharArray()) {
					int digit = Character.getNumericValue(ch);
					arr[digit][days1.get(i)] = 1;
				}
			}
		}

		List<Integer> days2 = getDays(subjects);
		String [] times2 = subjects.getTime().split(",");
		for(int i =0; i < days2.size();i++){
			for (char ch : times2[i].toCharArray()) {
				int digit = Character.getNumericValue(ch);
				if (arr[digit][days2.get(i)]==1)
					throw new AlreadyTimeException();
			}
		}
	}

	private void validateSameCourse(Long studentId, Long classId, Long year, Long s){
		if (courseRepository.existSubjects(year,s,classId,studentId))
			throw new AlreadyRegisterException();
	}

	private void validateSubject(Subjects subjects){
		if (subjects.getPersonnel() <= 0)
			throw new ClosedRegisterException();
	}

	private void validateCredit(List<Course> courses, Subjects subjects){
		Integer totalCredit = subjects.getCredit();
		for (Course course : courses){
			totalCredit +=  course.getSubjects().getCredit();
		}

		if (totalCredit > 22)
			throw new CreditExceedException();
	}

	public void deleteSubject(Long studentId, Long classId, Long year, Long s){
		lectureService.validateUser(studentId);
		validateSubject(classId);

		Semester semester = getSemester(studentId, year, s);
		courseRepository.deleteCourseBySemesterIdAndSubjectsId(semester.getId(), classId);
	}

	public void validateSubject(Long classId){

		if(!subjectsRepository.existsSubjectsById(classId))
			throw new SubjectNotFound();
	}

	public Semester getSemester(Long studentId, Long year, Long s){
		Semester semester = semesterRepository.findSemesterByUserAndYearAndSemester(studentId, year, s);
		if (semester == null)
			throw new SemesterNotFound();
		else return semester;
	}

}
