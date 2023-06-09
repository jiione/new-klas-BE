package com.SoftwareEngineering.AcademicAdmin.repository;

import com.SoftwareEngineering.AcademicAdmin.entity.Board;
import com.SoftwareEngineering.AcademicAdmin.entity.Post;
import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
	@Query(value = "SELECT b "
		+ "FROM Board b "
		+ "JOIN FETCH b.subjects s "
		+ "JOIN FETCH b.posts p "
		+ "WHERE s.id = :classId AND b.code = 0 "
		+ "ORDER BY p.time desc ")
	Board findNoticeByClassId(@Param("classId")Long classId);

	@Query(value = "SELECT b "
		+ "FROM Board b "
		+ "JOIN FETCH b.subjects s "
		+ "JOIN FETCH b.posts p "
		+ "WHERE s.id = :classId AND b.code = 3 "
		+ "ORDER BY p.time desc ")
	Board findAssignmentByClassId(@Param("classId")Long classId);

	@Query(value = "SELECT b "
		+ "FROM Board b "
		+ "JOIN FETCH b.subjects s "
		+ "JOIN FETCH b.posts p "
		+ "WHERE s.id = :classId AND b.code = 1 "
		+ "ORDER BY p.time desc ")
	Board findDataByClassId(@Param("classId")Long classId);


}
