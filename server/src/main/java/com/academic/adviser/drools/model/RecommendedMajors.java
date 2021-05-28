package com.academic.adviser.drools.model;

import com.academic.adviser.model.Major;

import java.util.ArrayList;
import java.util.List;

public class RecommendedMajors {
    private List<Major> majors;

    public RecommendedMajors() {
        this.majors = new ArrayList<>();
    }

    public void add(Major major) {
        majors.add(major);
    }

    public List<Major> getMajors() {
        return majors;
    }
}
