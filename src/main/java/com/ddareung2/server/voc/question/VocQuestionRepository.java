package com.ddareung2.server.voc.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ddareung2.server.voc.entity.VocQuestionEntity;

@Repository
public interface VocQuestionRepository extends JpaRepository<VocQuestion, Long> {
	void save(VocQuestionEntity vocQuestionEntity);
}
