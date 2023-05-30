package com.SoftwareEngineering.AcademicAdmin.dto.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeDetailResDTO {
    private String semesterName;

    @Builder.Default
    private List<GradeDetailPerClassDTO> classes = new ArrayList<>();
}
