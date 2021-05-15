package com.academic.adviser.constants;

public enum Operation {
    ADD("ADD"),
    SUB("SUB");

    private final String text;

    Operation(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
