package com.academic.adviser.service.impl;

import com.academic.adviser.drools.model.CareerTestNorm;
import com.academic.adviser.dto.CareerTestAnswerDTO;
import com.academic.adviser.dto.QuestionPairAnswerDTO;
import com.academic.adviser.mapper.QuestionPairAnswerMapper;
import com.academic.adviser.model.Candidate;
import com.academic.adviser.model.CareerArea;
import com.academic.adviser.model.QuestionPair;
import com.academic.adviser.repository.CandidateRepository;
import com.academic.adviser.repository.CareerAreaRepository;
import com.academic.adviser.repository.QuestionPairRepository;
import com.academic.adviser.rule.impl.CareerTestRule;
import com.academic.adviser.service.CareerTestService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerTestServiceImpl implements CareerTestService {
    @Autowired
    private KieContainer kContainer;

    @Autowired
    private CareerAreaRepository careerAreaRepository;

    @Autowired
    private QuestionPairRepository questionPairRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private List<CareerTestNorm> careerTestNormList;

    @Override
    public CareerArea submitCareerTest(CareerTestAnswerDTO careerTestAnswerDTO, String candidateEmail) {
        Candidate candidate = candidateRepository.findByEmailAddress(candidateEmail);

        CareerTestRule careerTestRule = new CareerTestRule(
                careerTestAnswerDTO,
                kContainer,
                questionPairRepository,
                careerAreaRepository,
                careerTestNormList,
                candidate);

        return (CareerArea) careerTestRule.runRule();
    }
}
