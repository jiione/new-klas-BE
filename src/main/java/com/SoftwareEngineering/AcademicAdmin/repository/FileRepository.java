package com.SoftwareEngineering.AcademicAdmin.repository;

import java.util.List;

import com.SoftwareEngineering.AcademicAdmin.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FileRepository extends JpaRepository<File, Long> {
	@Query(value = "SELECT f "
		+ "FROM File f "
		+ "JOIN FETCH f.post p "
		+ "WHERE p.id =:postId AND f.studentId =:studentId "
		+ "ORDER BY f.id desc ")
	List<File> findFilesByStudentId(@Param("postId")Long postId, @Param("studentId")Long studentId);
}
