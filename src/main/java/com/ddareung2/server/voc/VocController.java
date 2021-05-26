package com.ddareung2.server.voc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddareung2.server.voc.answer.VocAnswer;
import com.ddareung2.server.voc.answer.VocAnswerService;
import com.ddareung2.server.voc.entity.VocAnswerEntity;
import com.ddareung2.server.voc.entity.VocQuestionEntity;
import com.ddareung2.server.voc.question.VocQuestion;
import com.ddareung2.server.voc.question.VocQuestionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("voc")
public class VocController {
	
	private final VocQuestionService vocQuestionService;
	private final VocAnswerService vocAnswerService;
	
	@GetMapping()
	public List<VocQuestion> getVocQuestions() {
		return vocQuestionService.findAll();
	}
	
	@PostMapping()
	public ResponseEntity<VocQuestion> saveVocQuestion(
			@RequestBody VocQuestionEntity vocQuestionEntity) {
		vocQuestionService.save(vocQuestionEntity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/search/{id}")
	public ResponseEntity<Map<String, Object>> getVocQuestion(
			@PathVariable(value = "id") Long id) {
		Map<String, Object> voc = new HashMap<>();
		voc.put("QUESTION", vocQuestionService.findByVocQuestion(id));
		voc.put("ANSWER", vocAnswerService.findByVocAnswer(vocQuestionService.findByVocQuestion(id).get()));
		
		return new ResponseEntity<>(voc, HttpStatus.OK);
	}
	
	@GetMapping(value = "/answer")
	public List<VocAnswer> getVocAnswers() {
		return vocAnswerService.findAll();
	}
	
	@PostMapping(value = "/answer")
	public ResponseEntity<VocAnswer> saveVocAnswer(
			@RequestBody VocAnswerEntity vocAnswerEntity) {
		vocAnswerService.save(vocAnswerEntity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
