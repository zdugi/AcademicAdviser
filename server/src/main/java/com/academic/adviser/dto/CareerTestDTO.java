package com.academic.adviser.dto;

import java.util.List;

public class CareerTestDTO {
    private List<QuestionPairDTO> questions;

    public CareerTestDTO(List<QuestionPairDTO> questions) {
        this.questions = questions;
    }

    public List<QuestionPairDTO> getQuestions() {
        return questions;
    }
}
