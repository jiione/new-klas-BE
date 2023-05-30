package com.SoftwareEngineering.AcademicAdmin.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeAvgResDTO {
    private String semesterName;

    private Integer totalGrade;

    private Float gradeAverage;
}
