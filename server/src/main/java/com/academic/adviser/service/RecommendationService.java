package com.academic.adviser.service;

import com.academic.adviser.model.Candidate;
import com.academic.adviser.model.CareerArea;
import com.academic.adviser.model.City;
import com.academic.adviser.model.Major;

import java.util.List;

public interface RecommendationService {
    List<Major> getMajors(CareerArea finalArea, City desiredCity, Candidate candidate);
}
