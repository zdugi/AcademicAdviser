package com.academic.adviser.model;

import com.academic.adviser.constants.BigFiveTrait;
import com.academic.adviser.constants.Operation;

import javax.persistence.*;

@Entity
public class BigFiveQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private BigFiveTrait trait;

    @Column(nullable = false)
    private Operation operation;

    public BigFiveQuestion() {}

    public BigFiveQuestion(Integer id, String text, BigFiveTrait trait, Operation operation) {
        this.id = id;
        this.text = text;
        this.trait = trait;
        this.operation = operation;
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

    public BigFiveTrait getTrait() {
        return trait;
    }

    public void setTrait(BigFiveTrait trait) {
        this.trait = trait;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
