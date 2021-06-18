package com.academic.adviser.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;
import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AcademicLifeDTO {
    private List<String> majorNames;
    private Map<String, List<String>> dormitories;
    private Map<String, Object> lifeCosts;

    public AcademicLifeDTO() {}

    public AcademicLifeDTO(List<String> majorNames, Map<String, List<String>> dormitories, Map<String, Object> lifeCosts) {
        this.majorNames = majorNames;
        this.dormitories = dormitories;
        this.lifeCosts = lifeCosts;
    }
}
