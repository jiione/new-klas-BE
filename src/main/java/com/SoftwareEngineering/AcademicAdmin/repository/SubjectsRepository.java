package com.SoftwareEngineering.AcademicAdmin.repository;



import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubjectsRepository extends JpaRepository<Subjects, Long> , SubjectQueryDSL{
	Boolean existsSubjectsById(Long id);
}
