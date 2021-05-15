package com.academic.adviser.mapper;

import com.academic.adviser.dto.BigFiveQuestionDTO;
import com.academic.adviser.model.BigFiveQuestion;

public class BigFiveQuestionMapper {
    private BigFiveQuestion bigFiveQuestion;

    public BigFiveQuestionMapper(BigFiveQuestion bigFiveQuestion) {
        this.bigFiveQuestion = bigFiveQuestion;
    }

    public BigFiveQuestionDTO getDTO() {
        return new BigFiveQuestionDTO(this.bigFiveQuestion.getId(), this.bigFiveQuestion.getText());
    }

}
