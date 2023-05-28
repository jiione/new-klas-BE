package com.SoftwareEngineering.AcademicAdmin.exception.user;

import com.SoftwareEngineering.AcademicAdmin.exception.ExceptionMessage;

public class InvalidPassword extends RuntimeException{
	public InvalidPassword() { super(ExceptionMessage.INVALID_PASSWORD);}
}
