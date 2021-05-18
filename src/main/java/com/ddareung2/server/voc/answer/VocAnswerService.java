package com.ddareung2.server.voc.answer;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ddareung2.server.voc.dto.request.VocAnswerRequest;
import com.ddareung2.server.voc.question.VocQuestion;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VocAnswerService {
	private final VocAnswerRepository vocAnswerRepository;
	
	public List<VocAnswer> findAll() {
		return vocAnswerRepository.findAll();
	}
	
	public void save(VocAnswerRequest vocAnswerRequest) {
		vocAnswerRepository.save(vocAnswerRequest);
	}
	
	public Optional<List<VocAnswer>> findByVocAnswer(Optional<VocQuestion> vocQuestion) {
		return vocAnswerRepository.findByQuestionId(vocQuestion);
	}
}
