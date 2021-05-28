package com.ddareung2.server.voc.dto.request;

import com.ddareung2.server.admin.Admin;
import com.ddareung2.server.voc.answer.VocAnswer;
import com.ddareung2.server.voc.question.VocQuestion;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VocAnswerRequest {
	private long id;
	private VocQuestion questionId;
	private String content;
	private Admin adminId;
	
	public VocAnswer toEntity() {
		return VocAnswer.builder()
				.questionId(questionId)
				.content(content)
				.adminId(adminId)
				.build();
	}
}
