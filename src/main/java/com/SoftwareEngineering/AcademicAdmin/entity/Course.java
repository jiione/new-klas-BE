package com.SoftwareEngineering.AcademicAdmin.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "course")
public class Course {
    @EmbeddedId
    private CouresId id;

    private String score;

    @ManyToOne
    @JoinColumn(name = "semester_id", insertable = false, updatable = false)
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    private Subjects subjects;
}


