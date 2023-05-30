package com.SoftwareEngineering.AcademicAdmin.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeDetailPerClassDTO {
    private String className;

    private Integer credit;

    private String score;
}
