package com.SoftwareEngineering.AcademicAdmin.service;


import com.SoftwareEngineering.AcademicAdmin.dto.response.ScheduleDetailDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.ScheduleResDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.Course;
import com.SoftwareEngineering.AcademicAdmin.entity.Semester;
import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;
import com.SoftwareEngineering.AcademicAdmin.entity.User;
import com.SoftwareEngineering.AcademicAdmin.exception.semester.SemesterNotFound;
import com.SoftwareEngineering.AcademicAdmin.repository.BoardRepository;
import com.SoftwareEngineering.AcademicAdmin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class ProfessorMainService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    public List<ScheduleResDTO> getProfessorSchedule(Long studentId, String semester) {
        User user = userRepository.getUserByStudentId(studentId);
        Set<Semester> semesters = user.getSemesters();
        Semester sem = null;
        for (Semester s : semesters) {
            if (s.getName().equals(semester)) {
                sem = s;
                break;
            }
        }

        if (sem == null) throw new SemesterNotFound();

        Set<Course> courses = sem.getCourses();

        List<ScheduleResDTO>  scheduleResDTOS = new ArrayList<>();

        for(Course course : courses){
            Subjects subjects=course.getSubjects();

            List<ScheduleDetailDTO> scheduleDetailDTOS = new ArrayList<>();

            List<Integer> days = subjects.getDay().chars()
                    .mapToObj(Character::getNumericValue)
                    .collect(Collectors.toList());

            String[] parts = subjects.getTime().split(",");

            for(int i =0; i<days.size();i++){
                ScheduleDetailDTO scheduleDetailDTO = new ScheduleDetailDTO();
                scheduleDetailDTO.setDay(days.get(i));
                for (char ch : parts[i].toCharArray()) {
                    int digit = Character.getNumericValue(ch);
                    scheduleDetailDTO.getTime().add(digit);
                }

                scheduleDetailDTOS.add(scheduleDetailDTO);
            }


            ScheduleResDTO scheduleResDTO = ScheduleResDTO.builder()
                    .className(subjects.getName())
                    .professor(subjects.getProfessor())
                    .when(scheduleDetailDTOS)
                    .build();

            scheduleResDTOS.add(scheduleResDTO);
        }

        return scheduleResDTOS;

    }


}
