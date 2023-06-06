package com.SoftwareEngineering.AcademicAdmin.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SyllabusResDTO {
    private String name;

    private Integer credit;

    private String code;

    private String content;

    private String professor;

    private Long personnel;

    private List<ScheduleDetailDTO> when;
}
