package com.academic.adviser.service;

import com.academic.adviser.dto.CareerTestAnswerDTO;
import com.academic.adviser.model.CareerArea;

import java.util.List;

public interface CareerTestService {
    CareerArea submitCareerTest(CareerTestAnswerDTO careerTestAnswerDTO);
}
