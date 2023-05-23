package com.SoftwareEngineering.AcademicAdmin.exception.department;

import com.SoftwareEngineering.AcademicAdmin.exception.ExceptionMessage;

public class DepartmentNotFound extends RuntimeException{
	public DepartmentNotFound() { super(ExceptionMessage.DEPARTMENT_NOT_FOUND);}
}
