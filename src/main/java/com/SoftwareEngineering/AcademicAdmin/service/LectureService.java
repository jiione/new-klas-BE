package com.SoftwareEngineering.AcademicAdmin.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SoftwareEngineering.AcademicAdmin.dto.response.Lecture.AssignmentDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.Lecture.AssignmentResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.Lecture.LectureDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.Lecture.LectureResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.Lecture.NoticeDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.Lecture.NoticeResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.Lecture.SubjectDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.Board;
import com.SoftwareEngineering.AcademicAdmin.entity.Course;
import com.SoftwareEngineering.AcademicAdmin.entity.Post;
import com.SoftwareEngineering.AcademicAdmin.entity.Semester;
import com.SoftwareEngineering.AcademicAdmin.entity.User;
import com.SoftwareEngineering.AcademicAdmin.exception.user.UserNotFound;
import com.SoftwareEngineering.AcademicAdmin.repository.BoardRepository;
import com.SoftwareEngineering.AcademicAdmin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class LectureService {
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;

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

	public NoticeResDTO getNotice(Long studentId, Long classId){

		validateUser(studentId);
		return getNoticeList(classId);
	}

	private void validateUser(Long studentId){

		if(!userRepository.existsUserByStudentId(studentId))
			throw new UserNotFound();
	}

	private NoticeResDTO getNoticeList(Long classId){

		Board board = boardRepository.findNoticeByClassId(classId);
		List<Post> posts =  board.getPosts();

		List<NoticeDTO> noticeDTOS = posts.stream()
			.map(NoticeDTO::from)
			.collect(Collectors.toList());

		return NoticeResDTO.from(noticeDTOS);
	}

	public AssignmentResDTO getAssignment(Long studentId, Long classId){

		validateUser(studentId);
		return getAssignmentList(classId);
	}

	private AssignmentResDTO getAssignmentList(Long classId){

		Board board = boardRepository.findAssignmentByClassId(classId);
		List<Post> posts =  board.getPosts();

		List<AssignmentDTO> assignmentDTOS = posts.stream()
			.map(AssignmentDTO::from)
			.collect(Collectors.toList());

		return AssignmentResDTO.from(assignmentDTOS);
	}
}
