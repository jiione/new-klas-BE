package com.SoftwareEngineering.AcademicAdmin.dto.response.lecture;

import java.time.LocalDateTime;

import com.SoftwareEngineering.AcademicAdmin.entity.Post;

import lombok.Getter;

@Getter
public class AssignmentDetailDTO {

	private String title;
	private String content;
	private String writer;
	private LocalDateTime time;
	private LocalDateTime deadline;
	private Long view;
	private Boolean isSubmit;
	private String file;

	private AssignmentDetailDTO(Post post){
		this.title = post.getTitle();
		this.content = post.getContent();
		this.writer = post.getWriter();
		this.time = post.getTime();
		this.view = post.getView();
		this.deadline = post.getDeadline();
	}

	public static AssignmentDetailDTO of(Post post){
		return new AssignmentDetailDTO(post);
	}
}
