package com.academic.adviser.service.impl;

import com.academic.adviser.RunTemplateEngine;
import com.academic.adviser.constants.BigFiveTraitLevel;
import com.academic.adviser.drools.model.CareerAreas;
import com.academic.adviser.drools.model.CareerTest;
import com.academic.adviser.drools.model.Traits;
import com.academic.adviser.dto.*;
import com.academic.adviser.mapper.BigFiveQuestionMapper;
import com.academic.adviser.mapper.QuestionPairMapper;
import com.academic.adviser.model.BigFiveQuestion;
import com.academic.adviser.model.BigFiveResults;
import com.academic.adviser.model.CareerArea;
import com.academic.adviser.model.QuestionPair;
import com.academic.adviser.repository.BigFiveQuestionsRepository;
import com.academic.adviser.repository.CareerAreaRepository;
import com.academic.adviser.repository.QuestionPairRepository;
import com.academic.adviser.service.BigFiveService;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BigFiveServiceImpl implements BigFiveService {
    @Autowired
    private BigFiveQuestionsRepository bigFiveQuestionsRepository;

    @Autowired
    private CareerAreaRepository careerAreaRepository;

    @Autowired
    private QuestionPairRepository questionPairRepository;

    @Autowired
    private KieContainer kContainer;

    public BigFiveServiceImpl() {
        /*KieServices ks = KieServices.Factory.get();
        kContainer = ks
                .newKieContainer(ks.newReleaseId(
                        "org.adviserkjar", "server-kjar", "1.0-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        kScanner.start(10_000);*/
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
    public CareerTestDTO submitBigFiveSurvey(BigFiveSurveyAnswersDTO answers) {
        BigFiveResults bigFiveResults = new BigFiveResults();
        KieSession session = kContainer.newKieSession("ksession-bigfive-rules");

        session.insert(bigFiveResults);
        for(BigFiveAnswerDTO answerDTO : answers.getAnswers()) {
            session.insert(answerDTO);
        }
        //session.getAgenda().getAgendaGroup("big-five-questions").setFocus();
        //session.fireAllRules();

        CareerAreas careerAreas = new CareerAreas();
        Traits traits = new Traits();
        session.insert(traits);
        session.insert(careerAreas);
        for(CareerArea careerArea : careerAreaRepository.findAll()) {
            session.insert(careerArea);
        }
        //session.getAgenda().getAgendaGroup("detect-personality").setFocus();

        session.getAgenda().getAgendaGroup("big-five-questions").setFocus();

        int firedRules = session.fireAllRules();

        System.out.println(firedRules);

        session.getAgenda().getAgendaGroup("detect-personality").setFocus();

        firedRules = session.fireAllRules();

        System.out.println(firedRules);

        firedRules = session.fireAllRules();

        System.out.println(firedRules);

        System.out.println(bigFiveResults.getAgreeableness());
        System.out.println(bigFiveResults.getConscientiousness());
        System.out.println(bigFiveResults.getExtroversion());
        System.out.println(bigFiveResults.getNeuroticism());
        System.out.println(bigFiveResults.getOpenness());

        for(BigFiveTraitLevel level : traits.getTraits()) {
            System.out.println(level);
        }
        for(CareerArea careerArea : careerAreas.getCareerAreas()) {
            System.out.println(careerArea.getName());
        }

        CareerTest test = new CareerTest();

        session.insert(test);

        session.getAgenda().getAgendaGroup("career-questions").setFocus();
        firedRules = session.fireAllRules();

        System.out.println(firedRules);

        //System.out.println("Added: " + test.getQuestionPairs().size());
        //System.out.println("Done. " + areaA.getId() + " " + areaB.getId());
//        for (Integer pairId : test.getQuestionPairs()) {
//            Optional<QuestionPair> pair = questionPairRepository.findById(pairId);
//
//            System.out.println(pair.get().getQuestionA().getText());
//            System.out.println(pair.get().getQuestionB().getText());
//            System.out.println();
//        }

        System.out.println("Total pairs: " + test.getQuestionPairs().size());

        // mapping
        QuestionPairMapper questionPairMapper = new QuestionPairMapper();
        List<QuestionPairDTO> questions = new ArrayList<>();
        for (Integer id : test.getQuestionPairs())
            questions.add(
                    questionPairMapper.getDTO(questionPairRepository.getOne(id)));

        return new CareerTestDTO(questions);
    }
}
