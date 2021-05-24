package com.academic.adviser.mapper;

import com.academic.adviser.dto.QuestionPairDTO;
import com.academic.adviser.model.QuestionPair;
import com.academic.adviser.repository.QuestionPairRepository;

public class QuestionPairMapper {
    public QuestionPairDTO getDTO(QuestionPair questionPair) {
        return new QuestionPairDTO(
                questionPair.getId(),
                questionPair.getQuestionA().getText(),
                questionPair.getQuestionB().getText());
    }
}
