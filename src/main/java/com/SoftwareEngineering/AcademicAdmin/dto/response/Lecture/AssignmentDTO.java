package com.SoftwareEngineering.AcademicAdmin.dto.response.Lecture;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.SoftwareEngineering.AcademicAdmin.entity.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AssignmentDTO {
	private String title;

	private LocalDate time;

	private LocalDate deadline;

	private Long id;

	private AssignmentDTO(Post post){
		this.title = post.getTitle();
		this.time = post.getTime().toLocalDate();
		this.deadline = post.getDeadline().toLocalDate();
		this.id = post.getId();
	}

	public static AssignmentDTO from(Post post){
		return new AssignmentDTO(post);
	}
}
