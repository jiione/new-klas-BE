package com.SoftwareEngineering.AcademicAdmin.repository;

public interface CourseQueryDSL {
	Boolean existSubjects(Long year, Long semester, Long classId, Long studentId);
}
