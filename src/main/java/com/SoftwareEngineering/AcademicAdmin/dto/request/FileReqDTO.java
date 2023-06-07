package com.SoftwareEngineering.AcademicAdmin.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FileReqDTO {
    private Long postId;

    private Long studentId;

    private String title;
}
