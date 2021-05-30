package com.academic.adviser.controller;

import com.academic.adviser.drools.model.CareerTestNorm;
import com.academic.adviser.dto.CareerTestAnswerDTO;
import com.academic.adviser.service.CareerTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/career-test")
public class CareerTestController {
    @Autowired
    private CareerTestService careerTestService;

    @Autowired
    private List<CareerTestNorm> careerTestNormList;

    @PostMapping
    public ResponseEntity<?> submitTest(@RequestBody CareerTestAnswerDTO careerTestAnswerDTO) {
        careerTestService.submitCareerTest(careerTestAnswerDTO);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> test() {
        return new ResponseEntity<>(careerTestNormList, HttpStatus.OK);
    }
}
