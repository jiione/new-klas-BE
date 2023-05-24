package com.SoftwareEngineering.AcademicAdmin.exception;

public final class ExceptionMessage {

	private ExceptionMessage() {
	}

	//department
	public static final String DEPARTMENT_NOT_FOUND = "해당하는 학과 정보가 존재하지 않습니다";

	//회원가입
	public static final String STUDENT_ID_IS_EMPTY = "학번은 필수 값 입니다.";
	public static final String PASSWORD_IS_EMPTY = "비밀번호는 필수 값 입니다.";
	public static final String NAME_IS_EMPTY = "이름은 필수 값 입니다.";
	public static final String ADDRESS_IS_EMPTY = "주소는 필수 값 입니다";
	public static final String ROLE_IS_EMPTY = "역할은 필수 값 입니다";
	public static final String DEPARTMENT_IS_EMPTY = "학과는 필수 값 입니다.";

}
