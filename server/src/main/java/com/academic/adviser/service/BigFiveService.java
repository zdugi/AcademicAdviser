package com.academic.adviser.service;

import com.academic.adviser.drools.model.CareerTest;
import com.academic.adviser.dto.BigFiveSurveyAnswersDTO;
import com.academic.adviser.dto.BigFiveSurveyDTO;
import com.academic.adviser.dto.CareerTestDTO;

import java.io.IOException;


public interface BigFiveService {
    BigFiveSurveyDTO getBigFiveQuestions();

    CareerTestDTO submitBigFiveSurvey(BigFiveSurveyAnswersDTO answer, String candidateEmail) throws IOException;
}
