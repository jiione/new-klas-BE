package com.SoftwareEngineering.AcademicAdmin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResDto {
    private Long id;

    private String title;

    private String writer;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul", shape = JsonFormat.Shape.STRING)
    private LocalDateTime time;
}
