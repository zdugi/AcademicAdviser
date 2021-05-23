package com.academic.adviser.drools.model;

public class QuestionPair {
    private Integer id;
    private Integer questionAreaA;
    private Integer questionAreaB;

    public QuestionPair(
            Integer id,
            Integer questionAreaA,
            Integer questionAreaB) {
        this.id = id;
        this.questionAreaA = questionAreaA;
        this.questionAreaB = questionAreaB;
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuestionAreaA() {
        return questionAreaA;
    }

    public Integer getQuestionAreaB() {
        return questionAreaB;
    }
}
