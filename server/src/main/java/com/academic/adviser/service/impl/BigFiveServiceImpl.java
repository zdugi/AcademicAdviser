package com.academic.adviser.service.impl;

import com.academic.adviser.dto.BigFiveQuestionDTO;
import com.academic.adviser.mapper.BigFiveQuestionMapper;
import com.academic.adviser.model.BigFiveQuestion;
import com.academic.adviser.repository.BigFiveQuestionsRepository;
import com.academic.adviser.service.BigFiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BigFiveServiceImpl implements BigFiveService {
    @Autowired
    private BigFiveQuestionsRepository bigFiveQuestionsRepository;

    @Override
    public List<BigFiveQuestionDTO> getBigFiveQuestions() {
        List<BigFiveQuestion> questions = bigFiveQuestionsRepository.findAll();
        ArrayList<BigFiveQuestionDTO> questionDTOS = new ArrayList<>();

        for (BigFiveQuestion question : questions) {
            BigFiveQuestionMapper mapper = new BigFiveQuestionMapper(question);
            questionDTOS.add(mapper.getDTO());
        }

        return questionDTOS;
    }
}
