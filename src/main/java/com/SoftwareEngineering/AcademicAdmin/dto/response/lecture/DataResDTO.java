package com.SoftwareEngineering.AcademicAdmin.dto.response.lecture;

import java.time.LocalDateTime;

import com.SoftwareEngineering.AcademicAdmin.entity.Post;

import lombok.Getter;

@Getter
public class DataResDTO {
	private String title;
	private String content;
	private String writer;
	private LocalDateTime time;
	private Long view;
	private String file;

	private DataResDTO(Post post){
		this.title = post.getTitle();
		this.content = post.getContent();
		this.writer = post.getWriter();
		this.time = post.getTime();
		this.view = post.getView();
	}

	public static DataResDTO from(Post post){
		return new DataResDTO(post);
	}
}
