package com.academic.adviser.service.impl;

import com.academic.adviser.constants.BigFiveTraitLevel;
import com.academic.adviser.drools.model.RecommendedMajors;
import com.academic.adviser.model.Candidate;
import com.academic.adviser.model.CareerArea;
import com.academic.adviser.model.City;
import com.academic.adviser.model.Major;
import com.academic.adviser.repository.CareerAreaRepository;
import com.academic.adviser.repository.MajorRepository;
import com.academic.adviser.service.RecommendationService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    @Autowired
    private KieContainer kContainer;

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private CareerAreaRepository careerAreaRepository;

    @Override
    public List<Major> getMajors(CareerArea finalArea, City desiredCity, Candidate candidate) {
        RecommendedMajors recommendedMajors = new RecommendedMajors();

        KieSession session = kContainer.newKieSession("ksession-recommendation-rules");

        session.insert(recommendedMajors);

        List<Major> majors = majorRepository.findAll();
        for (Major major : majors)
            session.insert(major);

        session.insert(candidate);
        session.insert(desiredCity);
        session.insert(finalArea);

        session.fireAllRules();

        return recommendedMajors.getMajors();
    }
}
