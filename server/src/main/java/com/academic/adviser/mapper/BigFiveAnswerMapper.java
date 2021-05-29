package com.academic.adviser.mapper;


import com.academic.adviser.drools.model.BigFiveAnswer;
import com.academic.adviser.dto.BigFiveAnswerDTO;

public class BigFiveAnswerMapper {
    public BigFiveAnswer toModel(BigFiveAnswerDTO bigFiveAnswers) {
        return new BigFiveAnswer(
                bigFiveAnswers.getId(),
                bigFiveAnswers.getScore()
        );
    }
}
