package com.SoftwareEngineering.AcademicAdmin.repository;

import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectsRepository extends JpaRepository<Subjects, Long> {
}
