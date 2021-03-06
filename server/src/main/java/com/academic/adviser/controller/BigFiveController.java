package com.academic.adviser.controller;

import com.academic.adviser.dto.BigFiveAnswerDTO;
import com.academic.adviser.dto.BigFiveQuestionDTO;
import com.academic.adviser.dto.BigFiveSurveyAnswersDTO;
import com.academic.adviser.dto.BigFiveSurveyDTO;
import com.academic.adviser.service.BigFiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/big-five")
public class BigFiveController {
    @Autowired
    private BigFiveService bigFiveService;

    @GetMapping("/survey")
    public ResponseEntity<BigFiveSurveyDTO> getSurvey() {
        return new ResponseEntity<>(bigFiveService.getBigFiveQuestions(), HttpStatus.OK);
    }

    @PostMapping("/survey")
    public ResponseEntity<?> submitSurvey(@RequestBody BigFiveSurveyAnswersDTO surveyDTO,
                                          Principal principal) throws IOException {
        return new ResponseEntity<>(
                bigFiveService.submitBigFiveSurvey(surveyDTO, principal.getName()), HttpStatus.OK);
    }

    @PostMapping("/tracker")
    public ResponseEntity<?> quickAnswer(@RequestBody BigFiveAnswerDTO answerDTO, Principal principal) {
        bigFiveService.updateCertainty(answerDTO, principal.getName());
        return new ResponseEntity<>(answerDTO, HttpStatus.OK);
    }
}
