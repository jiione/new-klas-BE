package com.SoftwareEngineering.AcademicAdmin.dto.response.lecture;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeResDTO {

	private List<NoticeDTO> notices = new ArrayList<>();

	private NoticeResDTO (List<NoticeDTO> notices){
		this.notices = notices;
	}

	public static NoticeResDTO from(List<NoticeDTO>notices){
		return new NoticeResDTO(notices);
	}
}
