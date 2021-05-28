package com.academic.adviser.service.impl;

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
import com.academic.adviser.repository.BigFiveQuestionsRepository;
import com.academic.adviser.repository.CareerAreaRepository;
import com.academic.adviser.repository.QuestionPairRepository;
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
    public CareerTestDTO submitBigFiveSurvey(BigFiveSurveyAnswersDTO answers) {
        BigFiveResults bigFiveResults = new BigFiveResults();
        KieSession session = kContainer.newKieSession("ksession-bigfive-rules");

        session.insert(bigFiveResults);
        for(BigFiveAnswerDTO answerDTO : answers.getAnswers()) {
            session.insert(answerDTO);
        }

        CareerAreas careerAreas = new CareerAreas();
        Traits traits = new Traits();
        session.insert(traits);
        session.insert(careerAreas);
        for(CareerArea careerArea : careerAreaRepository.findAll()) {
            session.insert(careerArea);
        }

        session.getAgenda().getAgendaGroup("big-five-questions").setFocus();
        int firedRules = session.fireAllRules();
        System.out.println(firedRules);

        session.getAgenda().getAgendaGroup("detect-personality").setFocus();
        firedRules = session.fireAllRules();
        System.out.println(firedRules);

        firedRules = session.fireAllRules();
        System.out.println(firedRules);

        CareerTest test = new CareerTest();

        session.insert(test);
        session.getAgenda().getAgendaGroup("career-questions").setFocus();
        firedRules = session.fireAllRules();

        System.out.println(firedRules);

        // mapping
        QuestionPairMapper questionPairMapper = new QuestionPairMapper();
        List<QuestionPairDTO> questions = new ArrayList<>();
        for (Integer id : test.getQuestionPairs())
            questions.add(
                    questionPairMapper.getDTO(questionPairRepository.getOne(id)));

        return new CareerTestDTO(questions);
    }
}
