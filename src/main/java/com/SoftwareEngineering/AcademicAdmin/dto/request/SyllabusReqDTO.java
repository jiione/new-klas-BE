package com.SoftwareEngineering.AcademicAdmin.dto.request;

import com.SoftwareEngineering.AcademicAdmin.exception.ExceptionMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class SyllabusReqDTO {

    @NotNull(message = ExceptionMessage.SUBJECT_NOT_FOUND)
    private Long subjectId;

    @NotNull(message = ExceptionMessage.CONTENT_NOT_FOUND)
    private String subjectContent;
}
