package com.academic.adviser.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BigFiveSurveyAnswersDTO {
    private List<BigFiveAnswerDTO> answers;

    public List<BigFiveAnswerDTO> getAnswers() {
        return answers;
    }
}
