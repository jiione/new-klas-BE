package com.SoftwareEngineering.AcademicAdmin.exception.user;

import com.SoftwareEngineering.AcademicAdmin.exception.ExceptionMessage;

public class AdminNotDelete extends RuntimeException{
	public AdminNotDelete(){super(ExceptionMessage.ADMIN_NOT_DELETE);}
}
