package com.academic.adviser.drools.model;

public class BigFiveAnswer {
    private Integer id;
    private Integer score;

    public BigFiveAnswer() {}

    public BigFiveAnswer(Integer id, Integer score) {
        this.id = id;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public Integer getScore() {
        return score;
    }
}
