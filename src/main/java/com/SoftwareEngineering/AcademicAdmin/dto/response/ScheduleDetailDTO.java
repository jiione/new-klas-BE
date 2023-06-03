package com.SoftwareEngineering.AcademicAdmin.dto.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleDetailDTO {
    private Integer day;

    private List<Integer> time = new ArrayList<>();
}
