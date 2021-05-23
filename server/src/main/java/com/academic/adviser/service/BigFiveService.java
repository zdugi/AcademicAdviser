package com.academic.adviser.service;

import com.academic.adviser.dto.BigFiveSurveyAnswersDTO;
import com.academic.adviser.dto.BigFiveSurveyDTO;

import java.io.IOException;


public interface BigFiveService {
    BigFiveSurveyDTO getBigFiveQuestions();

    void submitBigFiveSurvey(BigFiveSurveyAnswersDTO answers) throws IOException;
}
