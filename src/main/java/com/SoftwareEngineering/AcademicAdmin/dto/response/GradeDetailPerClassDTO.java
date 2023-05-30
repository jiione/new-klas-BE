package com.SoftwareEngineering.AcademicAdmin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeDetailPerClassDTO {
    private String className;

    private Integer credit;

    private String score;
}
