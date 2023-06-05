package com.SoftwareEngineering.AcademicAdmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SoftwareEngineering.AcademicAdmin.entity.Semester;

public interface SemesterRepository extends JpaRepository<Semester, Long> {

	@Query(value = "SELECT s "
		+ "FROM Semester s "
		+ "JOIN FETCH s.user u "
		+ "WHERE u.studentId =:studentId AND s.year=:year AND s.semester =:semester")
	Semester findSemesterByUserAndYearAndSemester(@Param("studentId")Long studentId, @Param("year")Long year, @Param("semester")Long semester);
}
