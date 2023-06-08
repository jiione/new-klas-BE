package com.SoftwareEngineering.AcademicAdmin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseListResDTO {
    private String studentName;

    private Long studentId;

    private String studentScore;
}
