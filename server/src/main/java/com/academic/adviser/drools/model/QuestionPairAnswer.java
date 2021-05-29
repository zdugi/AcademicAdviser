package com.academic.adviser.drools.model;

public class QuestionPairAnswer {
    private Integer id;
    private Integer questionAScore;
    private Integer questionBScore;

    public QuestionPairAnswer() {}

    public QuestionPairAnswer(Integer id, Integer questionAScore, Integer questionBScore) {
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
