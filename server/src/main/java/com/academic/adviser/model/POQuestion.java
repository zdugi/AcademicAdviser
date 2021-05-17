package com.academic.adviser.model;

import javax.persistence.*;

@Entity
public class POQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "career_area_id", nullable = false)
    private CareerArea careerArea;

    public POQuestion() {}

    public POQuestion(Integer id, String text, CareerArea careerArea) {
        this.id = id;
        this.text = text;
        this.careerArea = careerArea;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CareerArea getCareerArea() {
        return careerArea;
    }

    public void setCareerArea(CareerArea careerArea) {
        this.careerArea = careerArea;
    }
}
