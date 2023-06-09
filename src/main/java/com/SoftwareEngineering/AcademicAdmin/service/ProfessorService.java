package com.SoftwareEngineering.AcademicAdmin.service;

import com.SoftwareEngineering.AcademicAdmin.dto.request.GradeReqDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.request.PostReqDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.request.PostUpdateReqDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.request.SyllabusReqDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.*;
import com.SoftwareEngineering.AcademicAdmin.entity.*;
import com.SoftwareEngineering.AcademicAdmin.exception.board.BoardNotFound;
import com.SoftwareEngineering.AcademicAdmin.exception.post.PostNotFound;
import com.SoftwareEngineering.AcademicAdmin.exception.subject.SubjectNotFound;
import com.SoftwareEngineering.AcademicAdmin.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfessorService {

    private final SubjectsRepository subjectsRepository;

    private final BoardRepository boardRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final CourseRepository courseRepository;

    public Long writeSyllabus(SyllabusReqDTO syllabusReqDTO) {
        Optional<Subjects> subjectOptional = subjectsRepository.findById(syllabusReqDTO.getSubjectId());

        if(subjectOptional.isPresent()) {
            Subjects subjects = subjectOptional.get();

            subjectsRepository.save(Subjects.builder()
                            .professor(subjects.getProfessor())
                            .id(subjects.getId())
                            .name(subjects.getName())
                            .credit(subjects.getCredit())
                            .time(subjects.getTime())
                            .day(subjects.getDay())
                            .code(subjects.getCode())
                            .content(syllabusReqDTO.getSubjectContent())
                            .year(subjects.getYear())
                            .semester(subjects.getSemester())
                            .personnel(subjects.getPersonnel())
                    .build());

            return subjects.getId();
        }
        else{
            throw new SubjectNotFound();
        }

    }

    public SyllabusResDTO getSyllabus(Long subjectId) {
        Optional<Subjects> subjectsOptional = subjectsRepository.findById(subjectId);

        if(subjectsOptional.isPresent()) {
            Subjects subjects = subjectsOptional.get();
            List<ScheduleDetailDTO> scheduleDetailDTOS = new ArrayList<>();

            List<Integer> days = subjects.getDay().chars()
                    .mapToObj(Character::getNumericValue)
                    .collect(Collectors.toList());

            String[] parts = subjects.getTime().split(",");

            for (int i = 0; i < days.size(); i++) {
                ScheduleDetailDTO scheduleDetailDTO = new ScheduleDetailDTO();
                scheduleDetailDTO.setDay(days.get(i));
                for (char ch : parts[i].toCharArray()) {
                    int digit = Character.getNumericValue(ch);
                    scheduleDetailDTO.getTime().add(digit);
                }

                scheduleDetailDTOS.add(scheduleDetailDTO);
            }

            return SyllabusResDTO.builder()
                    .professor(subjects.getProfessor())
                    .when(scheduleDetailDTOS)
                    .code(subjects.getCode())
                    .name(subjects.getName())
                    .credit(subjects.getCredit())
                    .personnel(subjects.getPersonnel())
                    .content(subjects.getContent())
                    .build();
        }
        else {
            throw new SubjectNotFound();
        }
    }

    public Long writePost(PostReqDTO postReqDTO) {
        Board board = boardRepository.findBoardByCodeAndSubjectId(postReqDTO.getCode(),postReqDTO.getSubjectId());

        if(board != null){
            Optional<Subjects> subjects = subjectsRepository.findById(postReqDTO.getSubjectId());

            Post post = Post.builder()
                    .view(0L)
                    .writer(subjects.get().getProfessor())
                    .title(postReqDTO.getTitle())
                    .content(postReqDTO.getContent())
                    .deadline(postReqDTO.getDeadline())
                    .board(board)
                    .build();

            postRepository.save(post);

            return post.getId();
        }
        else {
            throw new BoardNotFound();
        }
    }

    public Long giveGrade(GradeReqDTO gradeReqDTO){
        User user = userRepository.getUserByStudentId(gradeReqDTO.getStudentId());
        Semester semester = user.getLatestSemester();
        Set<Course> courses = semester.getCourses();

        for(Course course : courses){
            if(course.getSubjects().getId()==gradeReqDTO.getSubjectId()){
                courseRepository.save(Course.builder()
                                .id(course.getId())
                                .score(gradeReqDTO.getScore())
                                .semester(course.getSemester())
                                .subjects(course.getSubjects())
                        .build());
                return course.getId();
            }
        }

        return -1L;
    }

    public List<CourseListResDTO> getCourseStudentList(Long classId) {
        List<User> users = userRepository.getUserBySubjectId(classId);
        List<CourseListResDTO> courseListResDTOS = new ArrayList<>();
        for(User user : users){
            Set<Course> courses =user.getLatestSemester().getCourses();
            String score=null;

            for(Course course : courses){
                if(course.getSubjects().getId().equals(classId)){
                    score = course.getScore();
                    break;
                }
            }

            courseListResDTOS.add(CourseListResDTO.builder()
                    .studentId(user.getStudentId())
                    .studentName(user.getName())
                    .studentScore(score)
                    .build());
        }

        return courseListResDTOS;
    }

    public Long updatePost(PostUpdateReqDTO postUpdateReqDTO) {
        Optional<Post> postOptional = postRepository.findById(postUpdateReqDTO.getPostId());

        if(postOptional.isPresent()){
            Post post = postOptional.get();
            post.updatePost(postUpdateReqDTO.getTitle(), postUpdateReqDTO.getContent(), postUpdateReqDTO.getDeadline());
            return post.getId();
        }
        else{
            throw new PostNotFound();
        }
    }

    public void deletePost(Long id){
        postRepository.deleteById(id);
    }

    public PostDetailResDTO readAssignment(Long postId) {
        Post post = postRepository.findAssignmentPostByPostId(postId);
        List<File> files = post.getFile();
        List<FileDetailDTO> fileDetailDTOS = new ArrayList<>();

        for(File file : files){
            fileDetailDTOS.add(FileDetailDTO.builder()
                            .fileName(file.getFileName())
                            .title(file.getTitle())
                            .studentId(file.getStudentId())
                            .link("http://localhost:8080/file/download/"+file.getId())
                    .build());

        }

        PostDetailResDTO postDetailResDTO =PostDetailResDTO.builder()
                .content(post.getContent())
                .deadline(post.getDeadline())
                .files(fileDetailDTOS)
                .id(postId)
                .time(post.getTime())
                .writer(post.getWriter())
                .title(post.getTitle())
                .build();


        return postDetailResDTO;


    }
}
