package com.SoftwareEngineering.AcademicAdmin.exception.user;

import com.SoftwareEngineering.AcademicAdmin.exception.ExceptionMessage;

public class UserNotFound extends RuntimeException {
	public UserNotFound() { super(ExceptionMessage.USER_NOT_FOUND);}
}
