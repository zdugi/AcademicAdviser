package com.academic.adviser.service.impl;

import com.academic.adviser.constants.BigFiveTrait;
import com.academic.adviser.constants.BigFiveTraitLevel;
import com.academic.adviser.drools.model.*;
import com.academic.adviser.dto.*;
import com.academic.adviser.mapper.BigFiveAnswerMapper;
import com.academic.adviser.mapper.BigFiveQuestionMapper;
import com.academic.adviser.mapper.QuestionPairMapper;
import com.academic.adviser.model.*;
import com.academic.adviser.repository.BigFiveQuestionsRepository;
import com.academic.adviser.repository.CandidateRepository;
import com.academic.adviser.repository.CareerAreaRepository;
import com.academic.adviser.repository.QuestionPairRepository;
import com.academic.adviser.rule.impl.BigFiveCertaintyRule;
import com.academic.adviser.rule.impl.BigFiveRule;
import com.academic.adviser.service.BigFiveService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    private BigFiveCertaintyRule bigFiveCertaintyRule;

    private HashMap<Integer, CertaintyReport> certaintyReportHashMap;

    public BigFiveServiceImpl(@Autowired KieContainer kContainer) {
        this.kContainer = kContainer;
        this.certaintyReportHashMap = new HashMap<>();
        this.bigFiveCertaintyRule = new BigFiveCertaintyRule(kContainer);
    }

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
    public void updateCertainty(BigFiveAnswerDTO bigFiveAnswerDTO, String candidateEmail) {
        Candidate candidate = candidateRepository.findByEmailAddress(candidateEmail);
        Integer candidateId = candidate.getId();

        BigFiveFlyAnswer bigFiveFlyAnswer = new BigFiveFlyAnswer(bigFiveAnswerDTO, candidateId);

        if (!this.certaintyReportHashMap.containsKey(candidateId)) {
            CertaintyReport certaintyReport = new CertaintyReport(candidateId);
            this.certaintyReportHashMap.put(candidateId, certaintyReport);
            this.bigFiveCertaintyRule.update(certaintyReport, false);
            this.bigFiveCertaintyRule.update(bigFiveFlyAnswer, true);
            return;
        }

        this.bigFiveCertaintyRule.update(bigFiveFlyAnswer, true);
    }

    public CareerTestDTO submitBigFiveSurvey(BigFiveSurveyAnswersDTO answers, String candidateEmail) {
        Candidate candidate = candidateRepository.findByEmailAddress(candidateEmail);
        CertaintyReport certaintyReport = this.popBigFiveCertainty(candidate.getId());

        List<BigFiveCertainty> certainties =
                certaintyReport.getReport().keySet().stream().map(qid -> new BigFiveCertainty(
                        this.bigFiveQuestionsRepository.getOne(qid), certaintyReport.getReport().get(qid)))
                        .collect(Collectors.toList());

        // calculate averages
        HashMap<BigFiveTrait, Double> weights = new HashMap<>();
        for (BigFiveCertainty certainty : certainties) {
            certainty.getCertainty();
            if (weights.containsKey(certainty.getBigFiveQuestion().getTrait())) {
                weights.put(
                        certainty.getBigFiveQuestion().getTrait(),
                        weights.get(certainty.getBigFiveQuestion().getTrait()) + certainty.getCertainty());
            } else {
                weights.put(
                        certainty.getBigFiveQuestion().getTrait(),
                        certainty.getCertainty());
            }
        }

        Double extroversionWeight = weights.get(BigFiveTrait.EXTROVERSION) / 10.0;
        Double conscientiousnessWeight = weights.get(BigFiveTrait.CONSCIENTIOUSNESS) / 10.0;
        Double neuroticismWeight = weights.get(BigFiveTrait.NEUROTICISM) / 10.0;
        Double opennessWeight = weights.get(BigFiveTrait.OPENNESS) / 10.0;
        Double agreeablenessWeight = weights.get(BigFiveTrait.AGREEABLENESS) / 10.0;

        System.out.println(
                extroversionWeight + " " +
                        conscientiousnessWeight + " " +
                        neuroticismWeight + " " +
                        opennessWeight + " " +
                        agreeablenessWeight
                );

        BigFiveRule rule = new BigFiveRule(
                kContainer,
                answers,
                questionPairRepository,
                careerAreaRepository,
                extroversionWeight,
                conscientiousnessWeight,
                neuroticismWeight,
                opennessWeight,
                agreeablenessWeight);

        BigFiveResults bigFiveResults = rule.getBigFiveResults();

        CareerTestDTO careerTest = (CareerTestDTO) rule.runRule();

        bigFiveResults.setCertainties(certainties);

        candidate.setBigFiveResults(bigFiveResults);
        candidateRepository.save(candidate);

        return careerTest;
    }

    private CertaintyReport popBigFiveCertainty(Integer candidateId) {
        CertaintyReport certaintyReport = this.certaintyReportHashMap.get(candidateId);
        //this.certaintyReportHashMap.remove(candidateId);
        //TODO remove?
        return certaintyReport;
    }
}
