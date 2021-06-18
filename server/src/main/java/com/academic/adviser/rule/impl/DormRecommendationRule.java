package com.academic.adviser.rule.impl;

import com.academic.adviser.model.AcademicLife;
import com.academic.adviser.model.Candidate;
import com.academic.adviser.model.Dormitory;
import com.academic.adviser.model.Major;
import com.academic.adviser.repository.DormitoryRepository;
import com.academic.adviser.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.HashSet;
import java.util.List;

public class DormRecommendationRule implements Rule  {
    private KieContainer kContainer;
    private AcademicLife academicLife;
    private Candidate candidate;
    private DormitoryRepository dormitoryRepository;

    public DormRecommendationRule(
            KieContainer kContainer,
            List<Major> majors,
            Candidate candidate,
            DormitoryRepository dormitoryRepository
    ) {
        academicLife = new AcademicLife();
        academicLife.setMajors(new HashSet<>(majors));
        this.kContainer = kContainer;
        this.candidate = candidate;
        this.dormitoryRepository = dormitoryRepository;
    }

    @Override
    public Object runRule() {
        KieSession session = kContainer.newKieSession("ksession-recommendation-rules");
        session.getAgenda().getAgendaGroup("academic-life").setFocus();

        session.insert(academicLife);

        for (Major major : academicLife.getMajors())
            session.insert(major);

        for (Dormitory dorm : dormitoryRepository.findAll())
            session.insert(dorm);

        session.insert(candidate);
        session.insert(candidate.getBigFiveResults());

        session.fireAllRules();

        return academicLife;
    }
}
