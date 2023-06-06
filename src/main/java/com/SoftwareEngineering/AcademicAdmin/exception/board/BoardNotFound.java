package com.SoftwareEngineering.AcademicAdmin.exception.board;

import com.SoftwareEngineering.AcademicAdmin.exception.ExceptionMessage;

public class BoardNotFound extends RuntimeException{
    public BoardNotFound(){super(ExceptionMessage.BOARD_NOT_FOUND);}
}
