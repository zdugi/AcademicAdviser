package com.academic.adviser.controller;

import com.academic.adviser.dto.BigFiveQuestionDTO;
import com.academic.adviser.service.BigFiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/big-five")
public class BigFiveController {
    @Autowired
    private BigFiveService bigFiveService;

    @GetMapping("/survey")
    public ResponseEntity<List<BigFiveQuestionDTO>> getSurvey() {
        return new ResponseEntity<>(bigFiveService.getBigFiveQuestions(), HttpStatus.OK);
    }

    @PostMapping("/survey")
    public ResponseEntity<Void> submitSurvey() {
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
}
