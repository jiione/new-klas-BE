package com.SoftwareEngineering.AcademicAdmin.service;

import com.SoftwareEngineering.AcademicAdmin.dto.response.GradeAvgResDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.Semester;
import com.SoftwareEngineering.AcademicAdmin.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Transactional
public class GradeService {

    private final UserService userService;

    public Map<String, Object> getGrade(Long studentId) {
        User user = userService.getUser(studentId);
        List<Semester> semesters = user.getSemesters();
        List<GradeAvgResDTO> gradeAvgs = new ArrayList<>();

        for(Semester semester: semesters){

        }
    }

    private List<GradeAvgResDTO> getGradeAvg(List<Semester> semesters) {
        List<GradeAvgResDTO> gradeAvgs = new ArrayList<>();
        for (Semester semester : semesters) {
            GradeAvgResDTO gradeAvgResDTO = GradeAvgResDTO.builder()
                    .semesterName(semester.getName())
                    .totalGrade()
                    .gradeAverage()
                    .build();

            gradeAvgs.add(gradeAvgResDTO);
        }

        return gradeAvgs;
    }

}
