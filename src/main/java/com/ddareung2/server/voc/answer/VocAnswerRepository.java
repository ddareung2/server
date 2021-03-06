package com.ddareung2.server.voc.answer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ddareung2.server.voc.entity.VocAnswerEntity;

@Repository
public interface VocAnswerRepository extends JpaRepository<VocAnswerEntity, Long>{
	Optional<VocAnswerEntity> findVocAnswerByQuestionId(long questionId);
	void save(VocAnswer entity);
	List<VocAnswerEntity> findTop10ByOrderByCreatedAtDesc();
}
