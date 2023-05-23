package com.SoftwareEngineering.AcademicAdmin.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    /*
     * 0: 공지사항
     * 1: 자료실
     * 2: 강의 묻고 답하기
     * 4: 과제
     */
    private Integer code;

    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Post> posts;
}
