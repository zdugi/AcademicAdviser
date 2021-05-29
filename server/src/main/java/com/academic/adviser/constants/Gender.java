package com.academic.adviser.constants;

public enum Gender {
    M("M"),
    F("F");

    private final String text;

    Gender(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
