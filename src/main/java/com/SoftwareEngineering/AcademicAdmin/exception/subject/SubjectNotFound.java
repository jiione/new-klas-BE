package com.SoftwareEngineering.AcademicAdmin.exception.subject;

import com.SoftwareEngineering.AcademicAdmin.exception.ExceptionMessage;

public class SubjectNotFound extends RuntimeException{
	public SubjectNotFound() { super(ExceptionMessage.SUBJECT_NOT_FOUND);}
}
