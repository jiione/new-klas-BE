package com.SoftwareEngineering.AcademicAdmin.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "semester")
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "semster_id")
    private Long id;

    private Long year;

    private Long semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "semester",cascade = CascadeType.ALL)
    private List<Course> courses;

    public String getName(){
        return String.valueOf(year)+"학년도 "+String.valueOf(semester)+"학기";
    }
}
