package com.SoftwareEngineering.AcademicAdmin.dto.response.lecture;

import java.time.LocalDate;
import java.util.List;

import com.SoftwareEngineering.AcademicAdmin.entity.Post;

import lombok.Getter;

@Getter
public class DataDetailDTO {
	private String title;
	private String content;
	private String writer;
	private LocalDate time;
	private Integer result;
	private String link;
	private String fileName;

	private DataDetailDTO(Post post, Integer result, String link, String fileName){
		this.title = post.getTitle();
		this.content = post.getContent();
		this.writer = post.getWriter();
		this.time = post.getTime().toLocalDate();
		this.result = result;
		this.link = link;
		this.fileName = fileName;
	}

	public static DataDetailDTO of(Post post, Integer result, String link, String fileName){
		return new DataDetailDTO(post, result, link, fileName);
	}
}
