package com.SoftwareEngineering.AcademicAdmin.exception.register;

import com.SoftwareEngineering.AcademicAdmin.exception.ExceptionMessage;

public class AlreadyRegisterException extends RuntimeException {
	public AlreadyRegisterException() { super(ExceptionMessage.ALREADY_REGISTER);}
}
