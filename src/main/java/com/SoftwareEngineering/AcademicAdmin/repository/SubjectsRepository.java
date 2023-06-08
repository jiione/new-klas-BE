package com.SoftwareEngineering.AcademicAdmin.repository;



import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectsRepository extends JpaRepository<Subjects, Long> , SubjectQueryDSL{
	Boolean existsSubjectsById(Long id);


}
