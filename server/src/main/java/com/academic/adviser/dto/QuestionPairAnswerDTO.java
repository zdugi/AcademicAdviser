package com.academic.adviser.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class QuestionPairAnswerDTO {
    private Integer id;
    private Integer questionAScore;
    private Integer questionBScore;

    public Integer getId() {
        return id;
    }

    public Integer getQuestionAScore() {
        return questionAScore;
    }

    public Integer getQuestionBScore() {
        return questionBScore;
    }
}
