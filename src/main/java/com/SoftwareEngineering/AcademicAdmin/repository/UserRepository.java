package com.SoftwareEngineering.AcademicAdmin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

	boolean existsUserByStudentId(Long studentId);
}
