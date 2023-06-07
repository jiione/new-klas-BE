package com.SoftwareEngineering.AcademicAdmin.exception.post;

import com.SoftwareEngineering.AcademicAdmin.exception.ExceptionMessage;

public class PostNotFound extends RuntimeException{
	public PostNotFound(){super(ExceptionMessage.POST_NOT_FOUND);}
}
