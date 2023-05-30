package com.SoftwareEngineering.AcademicAdmin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeAvgResDTO {
    private String semesterName;

    private Integer totalGrade;

    private Float gradeAverage;
}
