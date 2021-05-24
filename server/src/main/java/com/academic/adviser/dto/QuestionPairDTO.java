package com.academic.adviser.dto;

public class QuestionPairDTO {
    private Integer id;
    private String questionA;
    private String questionB;

    public QuestionPairDTO(Integer id, String questionA, String questionB) {
        this.id = id;
        this.questionA = questionA;
        this.questionB = questionB;
    }

    public Integer getId() {
        return id;
    }

    public String getQuestionA() {
        return questionA;
    }

    public String getQuestionB() {
        return questionB;
    }
}
