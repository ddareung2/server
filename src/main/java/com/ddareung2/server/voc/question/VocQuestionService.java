package com.ddareung2.server.voc.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.ddareung2.server.voc.dto.request.VocQuestionRequest;
import com.ddareung2.server.voc.dto.response.VocQuestionResponse;
import com.ddareung2.server.voc.entity.VocQuestionEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VocQuestionService {
	private final VocQuestionRepository vocQuestionRepository;
	
	public List<VocQuestionResponse> findAll() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<VocQuestionEntity> questions = vocQuestionRepository.findAll();
		List<VocQuestionResponse> result = new ArrayList<>();
		for(VocQuestionEntity question : questions ) {
			result.add(modelMapper.map(question, VocQuestionResponse.class));
		}
		return result;
	}
	
	public void save(VocQuestionRequest vocQuestionRequest) {
		vocQuestionRepository.save(vocQuestionRequest.toEntity());
	}
	
	public VocQuestionResponse findByVocQuestion(Long id) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		Optional<VocQuestionEntity> question = vocQuestionRepository.findById(id);
		if(question.isPresent()) {
			return modelMapper.map(question.get(), VocQuestionResponse.class);
		}
		return null;
	}
	
}
