package com.SoftwareEngineering.AcademicAdmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SoftwareEngineering.AcademicAdmin.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>, CourseQueryDSL {
	@Query(value = "SELECT c "
		+ "FROM Course c "
		+ "JOIN FETCH c.subjects s "
		+ "JOIN FETCH c.semester cs "
		+ "JOIN FETCH cs.user u "
		+ "where u.studentId = :studentId and cs.year = :year and cs.semester = :semester")
	List<Course> getCoursesByStudentId(@Param("studentId")Long studentId, @Param("year")Long year, @Param("semester")Long semester);
}
