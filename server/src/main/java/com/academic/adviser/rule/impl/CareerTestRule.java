package com.academic.adviser.rule.impl;

import com.academic.adviser.drools.model.CareerTestNorm;
import com.academic.adviser.dto.CareerTestAnswerDTO;
import com.academic.adviser.dto.QuestionPairAnswerDTO;
import com.academic.adviser.mapper.QuestionPairAnswerMapper;
import com.academic.adviser.model.CareerArea;
import com.academic.adviser.model.QuestionPair;
import com.academic.adviser.repository.CareerAreaRepository;
import com.academic.adviser.repository.QuestionPairRepository;
import com.academic.adviser.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.List;

public class CareerTestRule implements Rule {
    private CareerTestAnswerDTO careerTestAnswerDTO;
    private KieContainer kContainer;
    private QuestionPairRepository questionPairRepository;
    private CareerAreaRepository careerAreaRepository;
    private List<CareerTestNorm> careerTestNormList;

    public CareerTestRule(
            CareerTestAnswerDTO careerTestAnswerDTO,
            KieContainer kContainer,
            QuestionPairRepository questionPairRepository,
            CareerAreaRepository careerAreaRepository,
            List<CareerTestNorm> careerTestNormList
    ) {
        this.careerTestAnswerDTO = careerTestAnswerDTO;
        this.kContainer = kContainer;
        this.questionPairRepository = questionPairRepository;
        this.careerAreaRepository = careerAreaRepository;
        this.careerTestNormList = careerTestNormList;
    }

    @Override
    public Object runRule() {
        KieSession session = kContainer.newKieSession("ksession-careertest-rules");
        CareerArea finalArea = new CareerArea();

        for(CareerArea careerArea : careerAreaRepository.findAll()) {
            session.insert(careerArea);
        }

        QuestionPairAnswerMapper questionPairAnswerMapper = new QuestionPairAnswerMapper();
        for(QuestionPairAnswerDTO answer : careerTestAnswerDTO.getAnswers()) {
            session.insert(questionPairAnswerMapper.toModel(answer));
        }

        for(QuestionPair pair : questionPairRepository.findAll()) {
            session.insert(pair);
        }

        for(CareerTestNorm norm : careerTestNormList) {
            session.insert(norm);
        }

        session.insert(finalArea);

        int firedRules = session.fireAllRules();
        System.out.println(firedRules);

        return finalArea;
    }
}
