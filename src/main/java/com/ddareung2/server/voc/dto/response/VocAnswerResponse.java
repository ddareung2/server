package com.ddareung2.server.voc.dto.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VocAnswerResponse {
	private long id;
	@NotNull private long questionId;
	@NotBlank private String content;
	@NotNull private long adminId;
}
