package com.SoftwareEngineering.AcademicAdmin.dto.response.lecture;

import java.time.LocalDate;

import com.SoftwareEngineering.AcademicAdmin.entity.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeDTO {

	private String title;

	private LocalDate time;

	private String writer;

	private Long id;

	private NoticeDTO(Post post){
		this.title = post.getTitle();
		this.time = post.getTime().toLocalDate();
		this.writer = post.getWriter();
		this.id = post.getId();
	}

	public static NoticeDTO from(Post post){
		return new NoticeDTO(post);
	}
}
