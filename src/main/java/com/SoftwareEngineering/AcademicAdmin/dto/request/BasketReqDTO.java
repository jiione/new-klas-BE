package com.SoftwareEngineering.AcademicAdmin.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.SoftwareEngineering.AcademicAdmin.exception.ExceptionMessage;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BasketReqDTO {

	@NotNull(message = ExceptionMessage.STUDENT_ID_IS_EMPTY)
	private Long studentId;

	@NotNull(message = ExceptionMessage.CLASS_ID_IS_EMPTY)
	private Long classId;
}
