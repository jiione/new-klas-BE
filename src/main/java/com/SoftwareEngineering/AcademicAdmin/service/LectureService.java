package com.SoftwareEngineering.AcademicAdmin.service;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.AssignmentDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.AssignmentDetailDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.AssignmentResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.DataResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.LectureDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.LectureResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.NoticeDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.NoticeResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.PostResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.ProfessorDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.SubjectDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.Board;
import com.SoftwareEngineering.AcademicAdmin.entity.Course;
import com.SoftwareEngineering.AcademicAdmin.entity.Post;
import com.SoftwareEngineering.AcademicAdmin.entity.Semester;
import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;
import com.SoftwareEngineering.AcademicAdmin.entity.User;
import com.SoftwareEngineering.AcademicAdmin.exception.post.PostNotFound;
import com.SoftwareEngineering.AcademicAdmin.exception.subject.SubjectNotFound;
import com.SoftwareEngineering.AcademicAdmin.exception.user.UserNotFound;
import com.SoftwareEngineering.AcademicAdmin.repository.BoardRepository;
import com.SoftwareEngineering.AcademicAdmin.repository.PostRepository;
import com.SoftwareEngineering.AcademicAdmin.repository.SubjectsRepository;
import com.SoftwareEngineering.AcademicAdmin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class LectureService {
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;
	private final SubjectsRepository subjectsRepository;
	private final PostRepository postRepository;

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
			.map(semester -> LectureDTO.of(semester, getSubjectInfo(semester.getCourses())))
			.collect(Collectors.toSet());

		return LectureResDTO.from(lectureDTOS);
	}

	public NoticeResDTO getNotice(Long studentId, Long classId){

		validateUser(studentId);
		return getNoticeList(classId);
	}

	public void validateUser(Long studentId){

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

	public ProfessorDTO getProfessor(Long classId){

		Subjects subjects = getSubjects(classId);
		return ProfessorDTO.from(subjects.getProfessor());
	}

	private Subjects getSubjects(Long classId){
		return subjectsRepository.findById(classId)
			.orElseThrow(SubjectNotFound::new);
	}

	public PostResDTO getNoticeDetail(Long postId){
		Post post = findPostOrElseThrow(postId);
		post.updateView();
		return PostResDTO.from(post);
	}

	private Post findPostOrElseThrow(Long postId){
		return postRepository.findById(postId)
			.orElseThrow(PostNotFound::new);
	}

	public AssignmentDetailDTO getAssignmentDetail(Long postId){
		Post post = findPostOrElseThrow(postId);
		// 과제 제출 여부 알아야함
		// 과제 있으면 과제 보이도록
		post.updateView();
		return AssignmentDetailDTO.of(post);
	}

	public DataResDTO getDataDetail(Long postId){
		Post post = findPostOrElseThrow(postId);
		post.updateView();
		return DataResDTO.from(post);

	}
}
