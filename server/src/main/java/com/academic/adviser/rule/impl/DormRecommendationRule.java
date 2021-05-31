package com.academic.adviser.rule.impl;

import com.academic.adviser.drools.model.AcademicLife;
import com.academic.adviser.model.Candidate;
import com.academic.adviser.model.Dormitory;
import com.academic.adviser.model.Major;
import com.academic.adviser.repository.DormitoryRepository;
import com.academic.adviser.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

public class DormRecommendationRule implements Rule  {
    private KieContainer kContainer;
    private List<Major> majors;
    private Candidate candidate;
    private DormitoryRepository dormitoryRepository;

    public DormRecommendationRule(
            KieContainer kContainer,
            List<Major> majors,
            Candidate candidate,
            DormitoryRepository dormitoryRepository
    ) {
        this.kContainer = kContainer;
        this.majors = majors;
        this.candidate = candidate;
        this.dormitoryRepository = dormitoryRepository;
    }

    @Override
    public Object runRule() {
        AcademicLife academicLife = new AcademicLife();

        KieSession session = kContainer.newKieSession("ksession-recommendation-rules");
        session.getAgenda().getAgendaGroup("academic-life").setFocus();

        session.insert(academicLife);

        for (Major major : majors)
            session.insert(major);

        for (Dormitory dorm : dormitoryRepository.findAll())
            session.insert(dorm);

        session.insert(candidate);
        session.insert(candidate.getBigFiveResults());

        session.fireAllRules();

        return academicLife;
    }
}
