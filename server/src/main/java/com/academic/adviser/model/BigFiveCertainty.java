package com.academic.adviser.model;

import javax.persistence.*;

@Entity
public class BigFiveCertainty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private BigFiveQuestion bigFiveQuestion;

    @Column(nullable = false)
    private Double certainty;

    public BigFiveCertainty() {
    }

    public BigFiveCertainty(BigFiveQuestion bigFiveQuestion, Double certainty) {
        this.bigFiveQuestion = bigFiveQuestion;
        this.certainty = certainty;
    }

    public Integer getId() {
        return id;
    }

    public BigFiveQuestion getBigFiveQuestion() {
        return bigFiveQuestion;
    }

    public Double getCertainty() {
        return certainty;
    }
}
