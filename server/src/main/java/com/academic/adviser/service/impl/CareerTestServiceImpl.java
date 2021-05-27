package com.academic.adviser.service.impl;

import com.academic.adviser.dto.CareerTestAnswerDTO;
import com.academic.adviser.dto.QuestionPairAnswerDTO;
import com.academic.adviser.service.CareerTestService;
import org.springframework.stereotype.Service;

@Service
public class CareerTestServiceImpl implements CareerTestService {
    @Override
    public void submitCareerTest(CareerTestAnswerDTO careerTestAnswerDTO) {
        for(QuestionPairAnswerDTO answer : careerTestAnswerDTO.getAnswers()) {
            System.out.println(answer.getId() + ":");
            System.out.println("\t-A:" + answer.getQuestionAScore());
            System.out.println("\t-B:" + answer.getQuestionBScore());
        }
    }
}
