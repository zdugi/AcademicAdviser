package com.academic.adviser.drools.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CareerTest {
    private Set<Integer> questionPairs;

    public CareerTest() {
        this.questionPairs = new HashSet<>();
    }

    public void addPair(Integer pair) {
        this.questionPairs.add(pair);
    }

    public List<Integer> getQuestionPairs() {
        return new ArrayList<>(questionPairs);
    }
}
