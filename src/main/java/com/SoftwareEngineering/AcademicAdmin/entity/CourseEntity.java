package com.SoftwareEngineering.AcademicAdmin.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CourseEntity {
    @EmbeddedId
    private CouresEntityId id;

    private String score;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    private SubjectsEntity subjects;
}


