package com.SoftwareEngineering.AcademicAdmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SoftwareEngineering.AcademicAdmin.entity.Basket;

public interface BasketRepository extends JpaRepository<Basket, Long> {

	@Query(value = "SELECT b "
		+ "FROM Basket b "
		+ "JOIN FETCH b.subjects s ")
	List<Basket> findBasketByStudentId();

}
