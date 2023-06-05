package com.SoftwareEngineering.AcademicAdmin.entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "subject")
public class Subjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long id;

    @Column(name = "subject_name", length = 20, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer credit;

    @Column(name = "lecture_time")
    private String time;

    @Column(name = "lecture_day", length = 20)
    private String day;

    @Column(name = "subject_code", length = 20, unique = true)
    private String code;

    @Column
    private String professor;

    @Column(name = "subject_content", length = 1000)
    private String content;

    private Long year;

    private Long semester;

    private Long personnel;

    public void updatePersonnel(){
        this.personnel -= 1;
    }
}
