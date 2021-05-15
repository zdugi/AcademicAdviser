package com.academic.adviser.repository;

import com.academic.adviser.model.BigFiveQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BigFiveQuestionsRepository extends JpaRepository<BigFiveQuestion, Integer> {
}
