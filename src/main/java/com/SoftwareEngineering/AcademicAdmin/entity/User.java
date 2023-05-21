package com.SoftwareEngineering.AcademicAdmin.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false, length = 20)
    private String name;

    @Column(name = "user_status", length = 20)
    private String status;


    @Column(nullable = false)
    private LocalDate birth;
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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Course> Coures;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
