package com.academic.adviser.drools.model;

public class BigFiveTemplate {
    private Integer id;
    private String trait;
    private String operation;

    public BigFiveTemplate() {}

    public BigFiveTemplate(Integer id, String trait, String operation) {
        this.id = id;
        this.trait = trait;
        this.operation = operation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
