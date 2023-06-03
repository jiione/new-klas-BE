package com.SoftwareEngineering.AcademicAdmin.repository;

import java.util.List;

import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface SubjectsRepository extends JpaRepository<Subjects, Long> {
	@Query(value = "SELECT distinct s "
		+ "FROM Subjects s "
		+ "JOIN FETCH s.course c "
		+ "JOIN FETCH c.semester cs "
		+ "JOIN FETCH cs.user u "
		+ "WHERE s.year =: year AND s.semester =: semester "
		+ "AND s.name LIKE %: search ")
	List<Subjects> findSubjectsByYearAndSemesterAndSearch(@Param("year") Long year, @Param("semester") Long semester, @Param("search") String search);

}
