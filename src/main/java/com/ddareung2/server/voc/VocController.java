package com.ddareung2.server.voc;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddareung2.server.voc.dto.request.VocQuestionRequest;
import com.ddareung2.server.voc.question.VocQuestion;
import com.ddareung2.server.voc.question.VocQuestionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("voc")
public class VocController {
	
	private final VocQuestionService vocQuestionService;
	
	@GetMapping()
	public List<VocQuestion> getVocQuestions() {
		return vocQuestionService.findAll();
	}
	
	@PostMapping()
	public ResponseEntity<?> saveVocQuestion(
			@RequestBody VocQuestionRequest vocQuestionRequest) {
		vocQuestionService.save(vocQuestionRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/search")
	public Optional<VocQuestion> getVocQuestion(
			@RequestParam(value = "id") Long id) {
		return vocQuestionService.findByVocQuestionId(id);
	}

}
