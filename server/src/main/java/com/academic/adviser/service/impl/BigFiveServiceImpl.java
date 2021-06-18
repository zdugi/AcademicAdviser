package com.academic.adviser.service.impl;

import com.academic.adviser.constants.BigFiveTraitLevel;
import com.academic.adviser.drools.model.CareerAreas;
import com.academic.adviser.drools.model.CareerTest;
import com.academic.adviser.drools.model.Traits;
import com.academic.adviser.dto.*;
import com.academic.adviser.mapper.BigFiveAnswerMapper;
import com.academic.adviser.mapper.BigFiveQuestionMapper;
import com.academic.adviser.mapper.QuestionPairMapper;
import com.academic.adviser.model.BigFiveQuestion;
import com.academic.adviser.model.BigFiveResults;
import com.academic.adviser.model.Candidate;
import com.academic.adviser.model.CareerArea;
import com.academic.adviser.repository.BigFiveQuestionsRepository;
import com.academic.adviser.repository.CandidateRepository;
import com.academic.adviser.repository.CareerAreaRepository;
import com.academic.adviser.repository.QuestionPairRepository;
import com.academic.adviser.rule.impl.BigFiveRule;
import com.academic.adviser.service.BigFiveService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BigFiveServiceImpl implements BigFiveService {
    @Autowired
    private BigFiveQuestionsRepository bigFiveQuestionsRepository;

    @Autowired
    private CareerAreaRepository careerAreaRepository;

    @Autowired
    private QuestionPairRepository questionPairRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private KieContainer kContainer;

    @Override
    public BigFiveSurveyDTO getBigFiveQuestions() {
        List<BigFiveQuestion> questions = bigFiveQuestionsRepository.findAll();
        ArrayList<BigFiveQuestionDTO> questionDTOS = new ArrayList<>();

        for (BigFiveQuestion question : questions) {
            BigFiveQuestionMapper mapper = new BigFiveQuestionMapper(question);
            questionDTOS.add(mapper.getDTO());
        }

        return new BigFiveSurveyDTO(questionDTOS);
    }

    @Override
    public CareerTestDTO submitBigFiveSurvey(BigFiveSurveyAnswersDTO answers, String candidateEmail) {
        BigFiveRule rule = new BigFiveRule(
                kContainer,
                answers,
                questionPairRepository,
                careerAreaRepository);

        Candidate candidate = candidateRepository.findByEmailAddress(candidateEmail);
        candidate.setBigFiveResults(rule.getBigFiveResults());
        candidateRepository.save(candidate);

        return (CareerTestDTO) rule.runRule();
    }
}
