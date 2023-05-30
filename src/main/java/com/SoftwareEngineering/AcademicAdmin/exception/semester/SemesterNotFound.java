package com.SoftwareEngineering.AcademicAdmin.exception.semester;

import com.SoftwareEngineering.AcademicAdmin.exception.ExceptionMessage;

public class SemesterNotFound extends RuntimeException{
	public SemesterNotFound() { super(ExceptionMessage.SEMESTER_NOT_FOUND);}
}
