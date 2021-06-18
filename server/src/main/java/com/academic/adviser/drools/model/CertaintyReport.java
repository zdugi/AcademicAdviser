package com.academic.adviser.drools.model;

import java.util.HashMap;

public class CertaintyReport {
    private HashMap<Integer, Double> report;
    private Integer candidateId;

    public CertaintyReport(Integer candidateId) {
        this.candidateId = candidateId;
        this.report = new HashMap<>();
    }

    public boolean exists(Object pair) {
        return this.report.containsKey(pair);
    }

    public void set(Integer pair, Double val) {
        this.report.put(pair, val);
    }

    public void update(Integer pair, Double factor) {
        this.report.put(pair, this.report.get(pair) * factor);
    }

    public HashMap<Integer, Double> getReport() {
        return report;
    }

    public Integer getCandidateId() {
        return candidateId;
    }
}
