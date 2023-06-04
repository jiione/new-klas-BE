package com.SoftwareEngineering.AcademicAdmin.repository;

import static com.SoftwareEngineering.AcademicAdmin.entity.QSubjects.*;

import java.util.List;

import javax.persistence.EntityManager;


import com.SoftwareEngineering.AcademicAdmin.entity.Subjects;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SubjectsRepositoryImpl implements SubjectQueryDSL{

	private final EntityManager em;

	public List<Subjects> findSubjectsByYearAndSemesterAndSearch(Long year, Long semester, String search){
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);

		if (search == null){
			return queryFactory
				.select(subjects)
				.from(subjects)
				.where(subjects.year.eq(year), subjects.semester.eq(semester))
				.fetch();
		}
		else{
			return queryFactory
				.select(subjects)
				.from(subjects)
				.where(subjects.year.eq(year), subjects.semester.eq(semester),
					subjects.professor.like("%" + search + "%").or(subjects.name.like("%" + search + "%")))
				.fetch();
		}

	}

}
