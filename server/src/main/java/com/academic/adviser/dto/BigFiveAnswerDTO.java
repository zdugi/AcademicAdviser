package com.academic.adviser.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BigFiveAnswerDTO {
    private Integer id;
    private Integer score;

    public BigFiveAnswerDTO() {
    }

    public BigFiveAnswerDTO(Integer id, Integer score) {
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
