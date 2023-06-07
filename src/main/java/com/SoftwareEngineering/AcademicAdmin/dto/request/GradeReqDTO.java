package com.SoftwareEngineering.AcademicAdmin.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GradeReqDTO {
    private Long studentId;

    private String score;

    private Long subjectId;
}
