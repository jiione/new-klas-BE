package com.SoftwareEngineering.AcademicAdmin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SoftwareEngineering.AcademicAdmin.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	@Query(value = "SELECT d "
		+ "FROM Department d "
		+ "WHERE d.name = :name")
	Optional<Department> findDepartmentByName(@Param("name")String name);
}
