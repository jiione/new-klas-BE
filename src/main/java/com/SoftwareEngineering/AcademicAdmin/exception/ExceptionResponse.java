package com.SoftwareEngineering.AcademicAdmin.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {
	private String message;
	private HttpStatus httpStatus;
	private LocalDateTime time;
}
