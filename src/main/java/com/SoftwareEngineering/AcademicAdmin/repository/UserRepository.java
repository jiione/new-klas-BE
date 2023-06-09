package com.SoftwareEngineering.AcademicAdmin.repository;

import java.util.List;
import java.util.Optional;

import com.SoftwareEngineering.AcademicAdmin.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.SoftwareEngineering.AcademicAdmin.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT u "
		+ "FROM User  u "
		+ "WHERE u.studentId = :studentId")
	Optional<User> findUserByStudentId(@Param("studentId")Long studentId);

	@Query(value = "SELECT distinct u "
		+ "FROM User u "
		+ "JOIN FETCH u.semesters s "
		+ "JOIN FETCH s.courses c "
		+ "JOIN FETCH c.subjects "
		+ "WHERE u.studentId in :studentId")
	User getUserByStudentId(@Param("studentId") Long studentId);

	@Query(value = "SELECT distinct u "
			+ "FROM User u "
			+ "JOIN FETCH u.semesters s "
			+ "JOIN FETCH s.courses c "
			+ "JOIN FETCH c.subjects cs "
			+ "WHERE cs.id in :subjectId and u.role = '학생' ")
	List<User> getUserBySubjectId(@Param("subjectId") Long subjectId);

	boolean existsUserByStudentId(Long studentId);

	@Query(value = "SELECT distinct u "
		+ "FROM User u "
		+ "JOIN FETCH u.department d "
		+ "WHERE u.studentId <> :studentId "
		+ "ORDER BY u.studentId ")
	List<User> getUsers(@Param("studentId") Long studentId);

	@Modifying
	@Transactional
	void deleteByStudentId(@Param("studentId") Long studentId);
}
