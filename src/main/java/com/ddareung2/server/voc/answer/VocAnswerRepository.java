package com.ddareung2.server.voc.answer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ddareung2.server.voc.dto.request.VocAnswerRequest;
import com.ddareung2.server.voc.question.VocQuestion;

@Repository
public interface VocAnswerRepository extends JpaRepository<VocAnswer, Long>{
	void save(VocAnswerRequest vocAnswerRequest);

	Optional<List<VocAnswer>> findByQuestionId(Optional<VocQuestion> vocQuestion);
}
