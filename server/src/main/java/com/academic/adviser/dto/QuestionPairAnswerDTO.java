package com.academic.adviser.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class QuestionPairAnswerDTO {
    private Integer id;
    private Integer questionAScore;
    private Integer questionBScore;

    public QuestionPairAnswerDTO() {}

    public QuestionPairAnswerDTO(Integer id, Integer questionAScore, Integer questionBScore) {
        this.id = id;
        this.questionAScore = questionAScore;
        this.questionBScore = questionBScore;
    }

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
