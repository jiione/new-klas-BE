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

	//로그인
	public static final String USER_NOT_FOUND = "해당하는 사용자가 존재하지 않습니다.";
	public static final String INVALID_PASSWORD = "비밀번호가 일치하지 않습니다.";

	//semester
	public static final String SEMESTER_NOT_FOUND = "학기가 존재하지 않습니다.";

	//subject
	public static final String SUBJECT_NOT_FOUND = "해당하는 과목은 존재하지 않습니다.";
	public static final String CLASS_ID_IS_EMPTY = "과목 번호는 필수 값 입니다.";
	public static final String CONTENT_NOT_FOUND = "내용이 존재하지 않습니다.";

	//수강신청
	public static final String ALREADY_REGISTER = "이미 등록된 강의 입니다";
	public static final	String CLOSED_REGISTER = "만석입니다.";
	public static final String ALREADY_TIME_REGISTER = "시간이 겹칩니다.";


	//board
	public static final String BOARD_NOT_FOUND = "게시판이 존재하지 않습니디ㅏ.";
	public static final String CREDIT_EXCEED = "학점이 초과되었습니다.";

	//post
	public static final String POST_NOT_FOUND = "게시글이 존재하지 않습니다.";

	//admin
	public static final	String IS_NOT_ADMIN = "관리자가 아닙니다";

}
