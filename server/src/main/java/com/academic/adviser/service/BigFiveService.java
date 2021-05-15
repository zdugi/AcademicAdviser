package com.academic.adviser.service;

import com.academic.adviser.dto.BigFiveSurveyAnswersDTO;
import com.academic.adviser.dto.BigFiveSurveyDTO;


public interface BigFiveService {
    BigFiveSurveyDTO getBigFiveQuestions();

    void submitBigFiveSurvey(BigFiveSurveyAnswersDTO answers);
}
