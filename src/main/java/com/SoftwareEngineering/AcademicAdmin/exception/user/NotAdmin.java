package com.SoftwareEngineering.AcademicAdmin.exception.user;

import com.SoftwareEngineering.AcademicAdmin.exception.ExceptionMessage;

public class NotAdmin extends RuntimeException{
	public NotAdmin() { super(ExceptionMessage.IS_NOT_ADMIN);}
}
