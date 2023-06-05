package com.SoftwareEngineering.AcademicAdmin.repository;

import static com.SoftwareEngineering.AcademicAdmin.entity.QCourse.*;

import javax.persistence.EntityManager;

import com.SoftwareEngineering.AcademicAdmin.entity.Course;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CourseRepositoryImpl implements CourseQueryDSL{
	private final EntityManager em;

	@Override
	public Boolean existSubjects(Long year, Long semester, Long classId, Long studentId) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		Integer fetchOne = queryFactory
			.selectOne()
			.from(course)
			.where(course.semester.user.studentId.eq(studentId), course.semester.year.eq(year)
				, course.semester.semester.eq(semester), course.subjects.id.eq(classId))
			.fetchFirst();
		return fetchOne != null;
	}
}
