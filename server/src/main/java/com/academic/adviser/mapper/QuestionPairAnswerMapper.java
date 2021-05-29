package com.academic.adviser.mapper;

import com.academic.adviser.drools.model.QuestionPairAnswer;
import com.academic.adviser.dto.QuestionPairAnswerDTO;

public class QuestionPairAnswerMapper {
    public QuestionPairAnswer toModel(QuestionPairAnswerDTO questionPairAnswerDTO) {
        return new QuestionPairAnswer(
                questionPairAnswerDTO.getId(),
                questionPairAnswerDTO.getQuestionAScore(),
                questionPairAnswerDTO.getQuestionBScore()
        );
    }
}
