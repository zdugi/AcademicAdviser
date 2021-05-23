package com.academic.adviser.repository;

import com.academic.adviser.model.QuestionPair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionPairRepository extends JpaRepository<QuestionPair, Integer> {
}
