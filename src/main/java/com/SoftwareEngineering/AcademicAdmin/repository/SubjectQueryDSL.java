package com.SoftwareEngineering.AcademicAdmin.repository;

import java.util.List;

import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;

public interface SubjectQueryDSL {
	List<Subjects> findSubjectsByYearAndSemesterAndSearch(Long year, Long semester, String search);
}
