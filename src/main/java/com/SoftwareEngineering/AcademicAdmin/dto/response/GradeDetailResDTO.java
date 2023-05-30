package com.SoftwareEngineering.AcademicAdmin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeDetailResDTO {
    private String semesterName;

    private List<GradeDetailPerClassDTO> classes = new ArrayList<>();
}
