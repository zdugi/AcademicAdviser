package com.academic.adviser.rule.impl;

import com.academic.adviser.drools.model.RecommendedMajors;
import com.academic.adviser.model.Candidate;
import com.academic.adviser.model.CareerArea;
import com.academic.adviser.model.City;
import com.academic.adviser.model.Major;
import com.academic.adviser.repository.MajorRepository;
import com.academic.adviser.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.List;

public class MajorRecommendationRule implements Rule {
    private KieContainer kContainer;
    private MajorRepository majorRepository;
    private CareerArea finalArea;
    private City desiredCity;
    private Candidate candidate;

    public MajorRecommendationRule(
            KieContainer kContainer,
            MajorRepository majorRepository,
            CareerArea finalArea,
            City desiredCity,
            Candidate candidate
    ) {
        this.kContainer = kContainer;
        this.majorRepository = majorRepository;
        this.finalArea = finalArea;
        this.desiredCity = desiredCity;
        this.candidate = candidate;
    }

    @Override
    public Object runRule() {
        RecommendedMajors recommendedMajors = new RecommendedMajors();

        KieSession session = kContainer.newKieSession("ksession-recommendation-rules");
        session.getAgenda().getAgendaGroup("major-recommend").setFocus();

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
