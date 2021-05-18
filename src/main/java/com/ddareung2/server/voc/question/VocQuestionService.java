package com.ddareung2.server.voc.question;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ddareung2.server.voc.dto.request.VocQuestionRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VocQuestionService {
	private final VocQuestionRepository vocQuestionRepository;
	
	public List<VocQuestion> findAll() {
		return vocQuestionRepository.findAll();
	}
	
	public void save(VocQuestionRequest vocQuestionRequest) {
		vocQuestionRepository.save(vocQuestionRequest);
	}
	
	public Optional<VocQuestion> findByVocQuestion(Long id) {
		return vocQuestionRepository.findById(id);
	}
	
}
