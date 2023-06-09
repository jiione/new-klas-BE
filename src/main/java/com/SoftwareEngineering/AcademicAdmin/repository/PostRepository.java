package com.SoftwareEngineering.AcademicAdmin.repository;

import com.SoftwareEngineering.AcademicAdmin.entity.Board;
import com.SoftwareEngineering.AcademicAdmin.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post,Long> {
	@Query(value = "SELECT p "
		+ "FROM Post p "
		+ "JOIN FETCH p.board b "
		+ "JOIN FETCH b.subjects s "
		+ "WHERE s.id =: classId And b.code = 2")
	Post findPostBySubjectId(@Param("classId")Long classId);

	@Query(value = "SELECT p " +
			"FROM Post p " +
			"JOIN FETCH p.file f " +
			"WHERE p.id = :postId ")
	Post findAssignmentPostByPostId(@Param("postId")Long postId);
}
