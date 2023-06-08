package com.SoftwareEngineering.AcademicAdmin.service;

import com.SoftwareEngineering.AcademicAdmin.dto.response.PostResDto;
import com.SoftwareEngineering.AcademicAdmin.dto.response.ScheduleDetailDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.ScheduleResDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.*;
import com.SoftwareEngineering.AcademicAdmin.exception.semester.SemesterNotFound;
import com.SoftwareEngineering.AcademicAdmin.repository.BoardRepository;
import com.SoftwareEngineering.AcademicAdmin.repository.SubjectsRepository;
import com.SoftwareEngineering.AcademicAdmin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class StudentMainService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    private final UserService userService;

    public List<PostResDto> getTop5Post(Long studentId) {
        User user = userService.getUser(studentId);

        Semester latestSemester = user.getLatestSemester();

        Set<Course> courses = latestSemester.getCourses();
        List<Post> posts = new ArrayList<>();

        for (Course course : courses) {
            List<Post> coursePosts = boardRepository.findLatestPostsByBoardCodeAndSubjectId(0, course.getSubjects().getId());
            posts.addAll(coursePosts);
        }

        List<Post> sortedPosts = posts.stream()
                .sorted(Comparator.comparing(Post::getTime).reversed())
                .limit(5)
                .collect(Collectors.toList());

        List<PostResDto> postResDtos = new ArrayList<>();
        for (Post post : sortedPosts) {
            postResDtos.add(PostResDto.builder()
                    .id(post.getId())
                    .time(post.getTime())
                    .title(post.getTitle())
                    .writer(post.getWriter())
                    .name(post.getBoard().getSubjects().getName())
                    .build());
        }

        return postResDtos;
    }

    public List<String> getUserSemesters(Long studentId) {
        User user = userService.getUser(studentId);
        Set<Semester> semesters = user.getSemesters();

        List<String> semesterList = new ArrayList<>();

        for (Semester semester : semesters) {
            semesterList.add(String.valueOf(semester.getYear()) + "년도 " + String.valueOf(semester.getSemester()) + "학기");
        }

        return semesterList;
    }

    public List<ScheduleResDTO> getStudentSchedule(Long studentId, Long year, Long semester) {
        User user = userRepository.getUserByStudentId(studentId);
        Set<Semester> semesters = user.getSemesters();
        Semester sem = null;
        for (Semester s : semesters) {
            if (s.getYear().equals(year) && s.getSemester().equals(semester)) {
                sem = s;
                break;
            }
        }

        if (sem == null) throw new SemesterNotFound();

        Set<Course> courses = sem.getCourses();

        List<ScheduleResDTO> scheduleResDTOS = new ArrayList<>();

        for (Course course : courses) {
            Subjects subjects = course.getSubjects();
            List<ScheduleDetailDTO> scheduleDetailDTOS = getScheduleDetail(subjects);

            ScheduleResDTO scheduleResDTO = ScheduleResDTO.builder()
                    .classId(subjects.getId())
                    .className(subjects.getName())
                    .professor(subjects.getProfessor())
                    .when(scheduleDetailDTOS)
                    .build();

            scheduleResDTOS.add(scheduleResDTO);
        }

        return scheduleResDTOS;

    }

    public List<ScheduleDetailDTO> getScheduleDetail(Subjects subjects) {
        List<ScheduleDetailDTO> scheduleDetailDTOS = new ArrayList<>();
        List<Integer> days = getDays(subjects);
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

        return scheduleDetailDTOS;
    }

    private List<Integer> getDays(Subjects subjects) {
        return subjects.getDay().chars()
                .mapToObj(Character::getNumericValue)
                .collect(Collectors.toList());
    }
}
