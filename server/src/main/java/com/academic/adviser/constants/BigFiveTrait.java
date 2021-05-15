package com.academic.adviser.constants;

public enum BigFiveTrait {
    E("EXTROVERSION"),
    C("CONSCIENTIOUSNESS"),
    N("NEUROTICISM"),
    O("OPENNESS"),
    A("AGREEABLENESS");

    private final String text;

    BigFiveTrait(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
