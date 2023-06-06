package com.SoftwareEngineering.AcademicAdmin.exception.register;

import com.SoftwareEngineering.AcademicAdmin.exception.ExceptionMessage;

public class AlreadyTimeException extends RuntimeException{
	public AlreadyTimeException() { super(ExceptionMessage.ALREADY_TIME_REGISTER);}
}
