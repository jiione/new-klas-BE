package com.SoftwareEngineering.AcademicAdmin.dto.response.lecture;

import java.time.LocalDate;

import com.SoftwareEngineering.AcademicAdmin.entity.Post;

import lombok.Getter;

@Getter
public class DataDTO {
	private String title;

	private LocalDate time;

	private String writer;

	private Long id;

	private DataDTO(Post post){
		this.title = post.getTitle();
		this.time = post.getTime().toLocalDate();
		this.writer = post.getWriter();
		this.id = post.getId();
	}

	public static DataDTO from(Post post){
		return new DataDTO(post);
	}
}
