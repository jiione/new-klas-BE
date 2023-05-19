package com.SoftwareEngineering.AcademicAdmin.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false, length = 20)
    private String name;

    @Column(name = "user_status", length = 20)
    private String status;


    @Column(nullable = false)
    private String birth;
    private Long grade;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(name = "student_id", unique = true)
    private Long studentId;

    @Column(name = "phone_number", length = 100)
    private String phoneNumber;

    @Column(length = 50, nullable = false)
    private String password;

    private String role;

    @OneToMany(mappedBy = "user")
    private List<CourseEntity> Coures;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;
}
