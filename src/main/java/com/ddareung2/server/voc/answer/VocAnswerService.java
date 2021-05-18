package com.ddareung2.server.voc.answer;

import java.util.List;
import java.util.Optional;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ddareung2.server.voc.dto.request.VocAnswerRequest;
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
	
	public void save(VocAnswerRequest vocAnswerRequest) {
		vocAnswerRepository.save(vocAnswerRequest);
		
		Optional<VocQuestion> question = vocQuestionRepository.findById(vocAnswerRequest.getQuestion_id());
		if(question.isPresent()) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(question.get().getEmail());
			message.setSubject("title");
			message.setText("content");
			mailSender.send(message);
		}
				
	}
	
	public Optional<List<VocAnswer>> findByVocAnswer(Optional<VocQuestion> vocQuestion) {
		return vocAnswerRepository.findByQuestionId(vocQuestion);
	}
}
