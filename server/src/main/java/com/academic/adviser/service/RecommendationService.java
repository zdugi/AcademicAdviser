package com.academic.adviser.service;

import com.academic.adviser.dto.AcademicLifeDTO;
import com.academic.adviser.model.*;

import java.util.List;

public interface RecommendationService {
    List<Major> getMajors(CareerArea finalArea, Integer city_id, String candidateEmail);

    void placeFinalResults(List<Major> majors, String candidateEmail);

    AcademicLifeDTO getFinalResults(String candidateEmail);
}
