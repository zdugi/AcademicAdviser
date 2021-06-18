package com.academic.adviser.controller;

import com.academic.adviser.drools.model.CareerTestNorm;
import com.academic.adviser.dto.CareerTestAnswerDTO;
import com.academic.adviser.model.Candidate;
import com.academic.adviser.model.CareerArea;
import com.academic.adviser.model.City;
import com.academic.adviser.model.Major;
import com.academic.adviser.service.CareerTestService;
import com.academic.adviser.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/career-test")
public class CareerTestController {
    @Autowired
    private CareerTestService careerTestService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private List<CareerTestNorm> careerTestNormList;

    @PostMapping
    public ResponseEntity<?> submitTest(
            @RequestBody CareerTestAnswerDTO careerTestAnswerDTO, Principal principal) {
        CareerArea finalArea = careerTestService.submitCareerTest(careerTestAnswerDTO);
        List<Major> majors = recommendationService.getMajors(
                finalArea,
                new City(0, "Beograd", 55356.0, 40000.0),
                principal.getName()
                );

        return new ResponseEntity<>(majors, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> test() {
        return new ResponseEntity<>(careerTestNormList, HttpStatus.OK);
    }
}
