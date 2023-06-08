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
public class ScheduleResDTO {
    private Long classId;

    private String className;

    private String professor;

    private List<ScheduleDetailDTO> when;
}
