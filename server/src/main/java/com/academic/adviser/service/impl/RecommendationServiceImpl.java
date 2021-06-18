package com.academic.adviser.service.impl;

import com.academic.adviser.dto.AcademicLifeDTO;
import com.academic.adviser.mapper.AcademicLifeMapper;
import com.academic.adviser.model.*;
import com.academic.adviser.repository.*;
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

    @Autowired
    private DormitoryRepository dormitoryRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<Major> getMajors(CareerArea finalArea, Integer cityId, String candidateEmail) {
        Candidate candidate = candidateRepository.findByEmailAddress(candidateEmail);
        City desiredCity = cityRepository.getOne(cityId);
        MajorRecommendationRule majorRecommendationRule = new MajorRecommendationRule(
                kContainer,
                majorRepository,
                finalArea,
                desiredCity,
                candidate);

        return (List<Major>) majorRecommendationRule.runRule();
    }

    @Override
    public void placeFinalResults(List<Major> majors, String candidateEmail) {
        Candidate candidate = candidateRepository.findByEmailAddress(candidateEmail);

        DormRecommendationRule dormRecommendationRule = new DormRecommendationRule(
                kContainer,
                majors,
                candidate,
                dormitoryRepository);

        AcademicLife academicLife = (AcademicLife) dormRecommendationRule.runRule();
        candidate.setAcademicLife(academicLife);
        candidateRepository.save(candidate);
    }

    @Override
    public AcademicLifeDTO getFinalResults(String candidateEmail) {
        Candidate candidate = candidateRepository.findByEmailAddress(candidateEmail);

        return new AcademicLifeMapper().toDto(candidate.getAcademicLife());
    }
}
