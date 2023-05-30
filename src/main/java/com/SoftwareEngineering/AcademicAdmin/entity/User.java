package com.SoftwareEngineering.AcademicAdmin.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.SoftwareEngineering.AcademicAdmin.dto.request.SignUpReqDTO;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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

    @Column(nullable = false, length = 100)
    private String address;

    @Column(name = "student_id", unique = true)
    private Long studentId;

    @Column(name = "phone_number", length = 100)
    private String phoneNumber;

    @Column(length = 50, nullable = false)
    private String password;

    private String role;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Semester> semesters = new HashSet<>();

    private User(SignUpReqDTO signUpReqDTO, Department department){
        this.name = signUpReqDTO.getName();
        this.address = signUpReqDTO.getAddress();
        this.birth = signUpReqDTO.getBirth();
        this.studentId = signUpReqDTO.getStudentId();
        this.password = signUpReqDTO.getPassword();
        this.phoneNumber = signUpReqDTO.getPhoneNumber();
        this.department = department;
        this.status = "재학";
        this.role = signUpReqDTO.getRole();
    }

    public static User of(SignUpReqDTO signUpReqDTO, Department department){
        return new User(signUpReqDTO, department);
    }

    public Semester getLatestSemester() {
        return semesters.stream()
                .max(Comparator.comparing(Semester::getYear)
                        .thenComparing(Semester::getSemester))
                .orElse(null);
    }
}
