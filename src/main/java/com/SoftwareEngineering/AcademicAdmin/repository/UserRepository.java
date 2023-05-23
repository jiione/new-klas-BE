package com.SoftwareEngineering.AcademicAdmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SoftwareEngineering.AcademicAdmin.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
