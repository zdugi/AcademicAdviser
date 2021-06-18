package com.academic.adviser.rule.impl;

import com.academic.adviser.drools.model.CareerAreas;
import com.academic.adviser.drools.model.CareerTest;
import com.academic.adviser.drools.model.Traits;
import com.academic.adviser.dto.BigFiveAnswerDTO;
import com.academic.adviser.dto.BigFiveSurveyAnswersDTO;
import com.academic.adviser.dto.CareerTestDTO;
import com.academic.adviser.dto.QuestionPairDTO;
import com.academic.adviser.mapper.BigFiveAnswerMapper;
import com.academic.adviser.mapper.QuestionPairMapper;
import com.academic.adviser.model.BigFiveResults;
import com.academic.adviser.model.CareerArea;
import com.academic.adviser.repository.CareerAreaRepository;
import com.academic.adviser.repository.QuestionPairRepository;
import com.academic.adviser.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

public class BigFiveRule implements Rule {
    private KieSession session;
    private BigFiveResults bigFiveResults;
    private BigFiveSurveyAnswersDTO answers;
    private QuestionPairRepository questionPairRepository;
    private CareerAreaRepository careerAreaRepository;

    private Double extroversionWeight;
    private Double conscientiousnessWeight;
    private Double neuroticismWeight;
    private Double opennessWeight;
    private Double agreeablenessWeight;

    public BigFiveRule(KieContainer kContainer,
                       BigFiveSurveyAnswersDTO answers,
                       QuestionPairRepository questionPairRepository,
                       CareerAreaRepository careerAreaRepository,
                       Double extroversionWeight,
                       Double conscientiousnessWeight,
                       Double neuroticismWeight,
                       Double opennessWeight,
                       Double agreeablenessWeight) {
        this.session = kContainer.newKieSession("ksession-bigfive-rules");
        this.bigFiveResults = new BigFiveResults();
        this.answers = answers;
        this.careerAreaRepository = careerAreaRepository;
        this.questionPairRepository = questionPairRepository;

        this.extroversionWeight = extroversionWeight;
        this.conscientiousnessWeight = conscientiousnessWeight;
        this.neuroticismWeight = neuroticismWeight;
        this.opennessWeight = opennessWeight;
        this.agreeablenessWeight = agreeablenessWeight;
    }

    @Override
    public Object runRule() {
        BigFiveAnswerMapper bigFiveAnswerMapper = new BigFiveAnswerMapper();

        session.setGlobal("extroversionWeight", this.extroversionWeight);
        session.setGlobal("conscientiousnessWeight", this.conscientiousnessWeight);
        session.setGlobal("neuroticismWeight", this.neuroticismWeight);
        session.setGlobal("opennessWeight", this.opennessWeight);
        session.setGlobal("agreeablenessWeight", this.agreeablenessWeight);

        session.insert(bigFiveResults);
        for(BigFiveAnswerDTO answerDTO : answers.getAnswers()) {
            session.insert(bigFiveAnswerMapper.toModel(answerDTO));
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

        //firedRules = session.fireAllRules();
        //System.out.println(firedRules);

        CareerTest test = new CareerTest();

        session.insert(test);
        session.getAgenda().getAgendaGroup("career-questions").setFocus();
        firedRules = session.fireAllRules();
        System.out.println(firedRules);

        QuestionPairMapper questionPairMapper = new QuestionPairMapper();
        List<QuestionPairDTO> questions = new ArrayList<>();
        for (Integer id : test.getQuestionPairs())
            questions.add(
                    questionPairMapper.getDTO(questionPairRepository.getOne(id)));

        return new CareerTestDTO(questions);
    }

    public BigFiveResults getBigFiveResults() {
        return bigFiveResults;
    }
}
