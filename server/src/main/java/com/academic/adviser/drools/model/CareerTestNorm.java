package com.academic.adviser.drools.model;

import com.academic.adviser.constants.Gender;
import org.kie.api.definition.type.Position;

public class CareerTestNorm {

    @Position(0)
    private Gender gender;

    @Position(1)
    private String careerArea;

    @Position(2)
    private Integer score;

    private Integer norm;

    public CareerTestNorm() {}

    public CareerTestNorm(Gender gender, String careerArea, Integer norm, Integer score) {
        this.gender = gender;
        this.careerArea = careerArea;
        this.norm = norm;
        this.score = score;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCareerArea() {
        return careerArea;
    }

    public void setCareerArea(String careerArea) {
        this.careerArea = careerArea;
    }

    public Integer getNorm() {
        return norm;
    }

    public void setNorm(Integer norm) {
        this.norm = norm;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
