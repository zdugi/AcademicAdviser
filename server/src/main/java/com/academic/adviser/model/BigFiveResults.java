package com.academic.adviser.model;

import com.academic.adviser.constants.TestConstants;

import javax.persistence.*;

@Entity
public class BigFiveResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer extroversion;

    @Column(nullable = false)
    private Integer areeableness;

    @Column(nullable = false)
    private Integer conscientiousness;

    @Column(nullable = false)
    private Integer neuroticism;

    @Column(nullable = false)
    private Integer openness;

    public BigFiveResults() {
        this.extroversion = TestConstants.EXTROVERSION_START;
        this.areeableness = TestConstants.AGREEABLENESS_START;
        this.conscientiousness = TestConstants.CONSCIENTIOUSNESS_START;
        this.neuroticism = TestConstants.NEUROTICISM_START;
        this.openness = TestConstants.OPENNESS_START;
    }

    public BigFiveResults(Integer extroversion, Integer areeableness, Integer conscientiousness, Integer neuroticism, Integer openness) {
        this.extroversion = extroversion;
        this.areeableness = areeableness;
        this.conscientiousness = conscientiousness;
        this.neuroticism = neuroticism;
        this.openness = openness;
    }

    public Integer getId() {
        return id;
    }

    public Integer getExtroversion() {
        return extroversion;
    }

    public void addExtroversion(Integer extroversion) {
        this.extroversion += extroversion;
    }

    public Integer getAreeableness() {
        return areeableness;
    }

    public void addAreeableness(Integer areeableness) {
        this.areeableness += areeableness;
    }

    public Integer getConscientiousness() {
        return conscientiousness;
    }

    public void addConscientiousness(Integer conscientiousness) {
        this.conscientiousness += conscientiousness;
    }

    public Integer getNeuroticism() {
        return neuroticism;
    }

    public void addNeuroticism(Integer neuroticism) {
        this.neuroticism += neuroticism;
    }

    public Integer getOpenness() {
        return openness;
    }

    public void addOpenness(Integer openness) {
        this.openness += openness;
    }
}
