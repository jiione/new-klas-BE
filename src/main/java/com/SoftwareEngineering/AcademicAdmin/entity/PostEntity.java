package com.SoftwareEngineering.AcademicAdmin.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String writer;

    @Column(name = "post_time")
    private Timestamp time;

    @Column(name = "post_content", length = 2000)
    private String content;

    @Column(name = "post_title", length = 100)
    private String title;

    @Column(name = "post_view")
    private String view;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity board;
}
