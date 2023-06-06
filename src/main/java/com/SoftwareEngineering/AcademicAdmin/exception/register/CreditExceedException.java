package com.SoftwareEngineering.AcademicAdmin.exception.register;

import com.SoftwareEngineering.AcademicAdmin.exception.ExceptionMessage;

public class CreditExceedException extends RuntimeException{
	public CreditExceedException() { super(ExceptionMessage.CREDIT_EXCEED);}
}
