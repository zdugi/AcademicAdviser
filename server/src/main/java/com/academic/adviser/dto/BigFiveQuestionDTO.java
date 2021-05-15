package com.academic.adviser.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BigFiveQuestionDTO {
    private Integer id;
    private String text;

    public BigFiveQuestionDTO(Integer id, String text) {
        this.id = id;
        this.text = text;
    }
}
