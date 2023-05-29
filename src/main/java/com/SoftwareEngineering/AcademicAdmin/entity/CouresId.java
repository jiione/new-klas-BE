package com.SoftwareEngineering.AcademicAdmin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouresId implements Serializable {
    @Column(name = "semester_id")
    private Long semesterId;

    @Column(name = "subject_id")
    private Long subjectId;
}
