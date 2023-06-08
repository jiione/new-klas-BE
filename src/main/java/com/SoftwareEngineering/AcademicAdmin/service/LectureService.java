package com.SoftwareEngineering.AcademicAdmin.service;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.AssignmentDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.AssignmentDetailDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.AssignmentResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.DataDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.lecture.DataDetailDTO;
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
import com.SoftwareEngineering.AcademicAdmin.entity.File;
import com.SoftwareEngineering.AcademicAdmin.entity.Post;
import com.SoftwareEngineering.AcademicAdmin.entity.Semester;
import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;
import com.SoftwareEngineering.AcademicAdmin.entity.User;
import com.SoftwareEngineering.AcademicAdmin.exception.post.PostNotFound;
import com.SoftwareEngineering.AcademicAdmin.exception.subject.SubjectNotFound;
import com.SoftwareEngineering.AcademicAdmin.exception.user.UserNotFound;
import com.SoftwareEngineering.AcademicAdmin.repository.BoardRepository;
import com.SoftwareEngineering.AcademicAdmin.repository.FileRepository;
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
	private final FileRepository fileRepository;

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
		if(board == null)
			return null;
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
		if(board == null)
			return null;
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

	public AssignmentDetailDTO getAssignmentDetail(Long postId, Long studentId){
		List<File> files =  fileRepository.findFilesByStudentId(postId, studentId);
		if (files.size() == 0) {
			Post samePost = findPostOrElseThrow(postId);
			return AssignmentDetailDTO.of(samePost, -1L,null, null);
		}
		// 과제 제출 여부 알아야함
		// 과제 있으면 과제 보이도록
		return AssignmentDetailDTO.of(files.get(0).getPost(), 1L, getLink(files.get(0).getId()), files.get(0).getFileName());
	}

	private String getLink(Long id){
		return "http://localhost:8080/file/download/" + id;
	}

	public DataDetailDTO getDataDetail(Long postId){

		List<File> files = fileRepository.findFilesByPostId(postId);
		if (files.size() == 0) {
			Post samePost = findPostOrElseThrow(postId);
			return DataDetailDTO.of(samePost, -1,null, null);
		}
		return DataDetailDTO.of(files.get(0).getPost(), 1, getLink(files.get(0).getId()), files.get(0).getFileName());

	}

	public PostResDTO getPlanning(Long studentId, Long classId){
		validateUser(studentId);
		Post post = postRepository.findPostBySubjectId(classId);
		post.updateView();
		return PostResDTO.from(post);
	}

	public DataResDTO getData(Long studentId, Long classId){
		validateUser(studentId);
		return getDataList(classId);
	}

	public DataResDTO getDataList(Long classId){
		Board board = boardRepository.findDataByClassId(classId);
		if(board == null)
			return null;
		List<Post> posts =  board.getPosts();

		List<DataDTO> dataDTOS = posts.stream()
			.map(DataDTO::from)
			.collect(Collectors.toList());

		return DataResDTO.from(dataDTOS);
	}
}
