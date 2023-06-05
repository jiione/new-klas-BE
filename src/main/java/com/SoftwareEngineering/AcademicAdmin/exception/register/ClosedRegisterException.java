package com.SoftwareEngineering.AcademicAdmin.exception.register;

import com.SoftwareEngineering.AcademicAdmin.exception.ExceptionMessage;

public class ClosedRegisterException extends RuntimeException{
	public ClosedRegisterException() { super(ExceptionMessage.CLOSED_REGISTER);}
}
