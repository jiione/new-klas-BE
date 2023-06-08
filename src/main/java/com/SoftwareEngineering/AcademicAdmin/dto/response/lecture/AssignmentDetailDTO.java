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
	private Long result;
	private String link;
	private String fileName;

	private AssignmentDetailDTO(Post post, Long result, String link, String fileName){
		this.title = post.getTitle();
		this.content = post.getContent();
		this.writer = post.getWriter();
		this.time = post.getTime();
		this.deadline = post.getDeadline();
		this.result = result;
		this.link = link;
		this.fileName = fileName;
	}

	public static AssignmentDetailDTO of(Post post, Long result, String link, String fileName){
		return new AssignmentDetailDTO(post, result, link, fileName);
	}
}
