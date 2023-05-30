package com.SoftwareEngineering.AcademicAdmin.service;

import com.SoftwareEngineering.AcademicAdmin.dto.response.GradeAvgResDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.GradeDetailPerClassDTO;
import com.SoftwareEngineering.AcademicAdmin.dto.response.GradeDetailResDTO;
import com.SoftwareEngineering.AcademicAdmin.entity.Course;
import com.SoftwareEngineering.AcademicAdmin.entity.Semester;
import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;
import com.SoftwareEngineering.AcademicAdmin.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
@Transactional
public class GradeService {

    private final UserService userService;


    public Map<String, Object> getGrade(Long studentId) {
        User user = userService.getUser(studentId);
        List<Semester> semesters = user.getSemesters();
        List<GradeAvgResDTO> gradeAvgs = new ArrayList<>();
        List<GradeDetailResDTO> gradeDetails = new ArrayList<>();

        for(Semester semester: semesters){
            String semesterName = semester.getName();
            Integer totalGrade = 0;
            Float tmpGradeSum = 0F;

            GradeDetailResDTO gradeDetail = new GradeDetailResDTO();

            gradeDetail.setSemesterName(semesterName);
            List<Course> courses = semester.getCourses();

            for(Course course : courses){
                changeScore(course.getScore());
                Subjects subjects = course.getSubjects();
                tmpGradeSum += changeScore(course.getScore());
                totalGrade += subjects.getCredit();

                gradeDetail.getClasses().add(GradeDetailPerClassDTO.builder()
                        .credit(subjects.getCredit())
                        .score(course.getScore())
                        .className(subjects.getName())
                        .build());
            }

            GradeAvgResDTO gradeAvg = GradeAvgResDTO.builder()
                    .semesterName(semesterName)
                    .gradeAverage(tmpGradeSum/courses.size())
                    .totalGrade(totalGrade)
                    .build();

            gradeAvgs.add(gradeAvg);
            gradeDetails.add(gradeDetail);
        }

        HashMap<String,Object> result = new HashMap<>();
        result.put("gradeAverages",gradeAvgs);
        result.put("grades", gradeDetails);

        return result;
    }

    private Float changeScore(String score){
        if(score.equals("A+")) return 4.5F;
        else if(score.equals("A")) return 4F;
        else if(score.equals("B+")) return 3.5F;
        else if(score.equals("B")) return 3F;
        else if(score.equals("C+")) return 2.5F;
        else if(score.equals("C")) return 2F;
        else if(score.equals("D+")) return 1.5F;
        else if(score.equals("D")) return 1F;
        else if(score.equals("F")) return 0F;

        return null;
    }


}
