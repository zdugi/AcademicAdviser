package com.academic.adviser.service;

import com.academic.adviser.model.*;

import java.util.List;

public interface RecommendationService {
    List<Major> getMajors(CareerArea finalArea, City desiredCity, Candidate candidate);

    List<Dormitory> getDorms(List<Major> majors, Candidate candidate);
}
