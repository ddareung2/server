package com.ddareung2.server.voc.answer;

import java.util.List;
import java.util.Optional;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ddareung2.server.voc.entity.VocAnswerEntity;
import com.ddareung2.server.voc.question.VocQuestion;
import com.ddareung2.server.voc.question.VocQuestionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VocAnswerService {
	private final VocAnswerRepository vocAnswerRepository;
	private final VocQuestionRepository vocQuestionRepository;
	private final JavaMailSender mailSender;
	
	public List<VocAnswer> findAll() {
		return vocAnswerRepository.findAll();
	}
	
	public void save(VocAnswerEntity vocAnswerEntity) {
		vocAnswerRepository.save(vocAnswerEntity);
		
		Optional<VocQuestion> question = vocQuestionRepository.findById(vocAnswerEntity.getQuestionId());
		if(question.isPresent()) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(question.get().getEmail());
			message.setSubject("title");
			message.setText("content");
			mailSender.send(message);
		}
				
	}
	
	public Optional<List<VocAnswer>> findByVocAnswer(VocQuestion vocQuestion) {
		return vocAnswerRepository.findByQuestionId(vocQuestion);
	}
}
