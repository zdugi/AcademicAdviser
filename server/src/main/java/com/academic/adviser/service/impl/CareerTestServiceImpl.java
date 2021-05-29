package com.academic.adviser.service.impl;

import com.academic.adviser.dto.CareerTestAnswerDTO;
import com.academic.adviser.dto.QuestionPairAnswerDTO;
import com.academic.adviser.mapper.QuestionPairAnswerMapper;
import com.academic.adviser.model.CareerArea;
import com.academic.adviser.model.QuestionPair;
import com.academic.adviser.repository.CareerAreaRepository;
import com.academic.adviser.repository.QuestionPairRepository;
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

    @Override
    public List<CareerArea> submitCareerTest(CareerTestAnswerDTO careerTestAnswerDTO) {
        for(QuestionPairAnswerDTO answer : careerTestAnswerDTO.getAnswers()) {
            System.out.println(answer.getId() + ":");
            System.out.println("\t-A:" + answer.getQuestionAScore());
            System.out.println("\t-B:" + answer.getQuestionBScore());
        }

        KieSession session = kContainer.newKieSession("ksession-careertest-rules");

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

        int firedRules = session.fireAllRules();

        return null;
    }
}
