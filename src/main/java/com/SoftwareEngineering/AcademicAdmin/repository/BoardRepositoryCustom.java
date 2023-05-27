package com.SoftwareEngineering.AcademicAdmin.repository;

import com.SoftwareEngineering.AcademicAdmin.entity.Post;

import java.util.List;

public interface BoardRepositoryCustom {
    List<Post> findLatestPostsByBoardCode(int code);
    List<Post> findLatestPostsByBoardCodeAndSubjectId(int code, Long subjectId);
}
