package com.academic.adviser.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CareerTestAnswerDTO {
    private List<QuestionPairAnswerDTO> answers;

    public List<QuestionPairAnswerDTO> getAnswers() {
        return answers;
    }
}
