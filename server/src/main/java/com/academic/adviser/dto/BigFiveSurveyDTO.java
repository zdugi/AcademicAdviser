package com.academic.adviser.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BigFiveSurveyDTO {
    private List<BigFiveQuestionDTO> questionDTOList;

    public BigFiveSurveyDTO(List<BigFiveQuestionDTO> questions) {
        this.questionDTOList = questions;
    }
}
