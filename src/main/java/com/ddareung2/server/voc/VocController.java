package com.ddareung2.server.voc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddareung2.server.voc.answer.VocAnswer;
import com.ddareung2.server.voc.answer.VocAnswerService;
import com.ddareung2.server.voc.dto.request.VocAnswerRequest;
import com.ddareung2.server.voc.dto.request.VocQuestionRequest;
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
			@RequestBody VocQuestionRequest vocQuestionRequest) {
		vocQuestionService.save(vocQuestionRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/search")
	public Map<String, Object> getVocQuestion(
			@RequestParam(value = "id") Long id) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("QUESTION", vocQuestionService.findByVocQuestion(id));
		map.put("ANSWER", vocAnswerService.findByVocAnswer(vocQuestionService.findByVocQuestion(id)));
		return map;
	}
	
	@GetMapping(value = "/answer")
	public List<VocAnswer> getVocAnswers() {
		return vocAnswerService.findAll();
	}
	
	@PostMapping(value = "/answer")
	public ResponseEntity<VocAnswer> saveVocAnswer(
			@RequestBody VocAnswerRequest vocAnswerRequest) {
		vocAnswerService.save(vocAnswerRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
