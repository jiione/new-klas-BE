package com.SoftwareEngineering.AcademicAdmin.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String writer;

    @Column(name = "post_time")
    private LocalDateTime time;

    @Column(name = "post_content", length = 2000)
    private String content;

    @Column(name = "post_title", length = 100)
    private String title;

    @Column(name = "post_view")
    private Long view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private LocalDateTime deadline;

    @PrePersist
    public void prePersist() {
        this.time = LocalDateTime.now();
    }
}
