package com.ddareung2.server.voc.dto.response;

import java.util.List;
import java.util.Optional;

import com.ddareung2.server.voc.answer.VocAnswer;
import com.ddareung2.server.voc.question.VocQuestion;

import lombok.Data;

@Data
public class VocResponse {
	public Optional<List<VocAnswer>> vocAnswer;
	public Optional<VocQuestion> vocQuestion;
}
