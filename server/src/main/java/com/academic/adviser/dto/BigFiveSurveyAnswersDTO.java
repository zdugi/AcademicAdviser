package com.academic.adviser.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BigFiveSurveyAnswersDTO {
    private List<BigFiveAnswerDTO> answers;

    public BigFiveSurveyAnswersDTO() {}

    public BigFiveSurveyAnswersDTO(List<BigFiveAnswerDTO> answers) {
        this.answers = answers;
    }

    public List<BigFiveAnswerDTO> getAnswers() {
        return answers;
    }
}
