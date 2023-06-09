package com.SoftwareEngineering.AcademicAdmin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PostDetailResDTO {

    private Long id;

    private String title;

    private String content;

    private String writer;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul", shape = JsonFormat.Shape.STRING)
    private LocalDateTime time;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul", shape = JsonFormat.Shape.STRING)
    private LocalDateTime deadline;

    private List<FileDetailDTO> files;

}
