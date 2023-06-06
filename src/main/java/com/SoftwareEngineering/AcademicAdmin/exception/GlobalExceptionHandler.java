package com.SoftwareEngineering.AcademicAdmin.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.SoftwareEngineering.AcademicAdmin.exception.department.DepartmentNotFound;
import com.SoftwareEngineering.AcademicAdmin.exception.register.AlreadyRegisterException;
import com.SoftwareEngineering.AcademicAdmin.exception.register.AlreadyTimeException;
import com.SoftwareEngineering.AcademicAdmin.exception.register.ClosedRegisterException;
import com.SoftwareEngineering.AcademicAdmin.exception.register.CreditExceedException;

import com.SoftwareEngineering.AcademicAdmin.exception.semester.SemesterNotFound;
import com.SoftwareEngineering.AcademicAdmin.exception.subject.SubjectNotFound;
import com.SoftwareEngineering.AcademicAdmin.exception.user.InvalidPassword;
import com.SoftwareEngineering.AcademicAdmin.exception.user.UserNotFound;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DepartmentNotFound.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse departmentNotFoundException(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
		return new ExceptionResponse(e.getBindingResult().getAllErrors().get(0).getDefaultMessage()
			, HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(UserNotFound.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse userNotFoundException(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(InvalidPassword.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse invalidPasswordException(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(SemesterNotFound.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse semesterNotFoundException(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(SubjectNotFound.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse subjectNotFoundException(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(AlreadyRegisterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse alreadyRegisterException(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(ClosedRegisterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse closedRegisterException(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(AlreadyTimeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse alreadyTimeException(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(CreditExceedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse creditExceedException(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

}
