package com.academic.adviser.service.impl;

import com.academic.adviser.model.*;
import com.academic.adviser.repository.CareerAreaRepository;
import com.academic.adviser.repository.MajorRepository;
import com.academic.adviser.rule.impl.DormRecommendationRule;
import com.academic.adviser.rule.impl.MajorRecommendationRule;
import com.academic.adviser.service.RecommendationService;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        MajorRecommendationRule majorRecommendationRule = new MajorRecommendationRule(
                kContainer,
                majorRepository,
                finalArea,
                desiredCity,
                candidate);

        return (List<Major>) majorRecommendationRule.runRule();
    }

    @Override
    public List<Dormitory> getDorms(List<Major> majors, Candidate candidate) {
        DormRecommendationRule dormRecommendationRule = new DormRecommendationRule(
                kContainer,
                majors,
                candidate);

        return (List<Dormitory>) dormRecommendationRule.runRule();
    }
}
