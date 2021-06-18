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
import org.springframework.security.core.parameters.P;
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

    @PostMapping("/{cityId}")
    public ResponseEntity<?> submitTest(
            @PathVariable("cityId") Integer cityId,
            @RequestBody CareerTestAnswerDTO careerTestAnswerDTO,
            Principal principal
            ) {
        CareerArea finalArea = careerTestService.submitCareerTest(careerTestAnswerDTO, principal.getName());
        List<Major> majors = recommendationService.getMajors(
                finalArea,
                cityId,
                principal.getName()
                );
        recommendationService.placeFinalResults(majors, principal.getName());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getFinalResult(Principal principal) {
        try {
            return new ResponseEntity<>(
                    recommendationService.getFinalResults(principal.getName()), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
