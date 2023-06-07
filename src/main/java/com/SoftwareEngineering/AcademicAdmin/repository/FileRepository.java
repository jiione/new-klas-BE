package com.SoftwareEngineering.AcademicAdmin.repository;

import com.SoftwareEngineering.AcademicAdmin.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
