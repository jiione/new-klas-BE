package com.SoftwareEngineering.AcademicAdmin.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BoardEntity {
    @Id
    @Column(name = "board_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectsEntity subjects;

    @OneToMany(mappedBy = "board")
    private List<PostEntity> posts;
}
