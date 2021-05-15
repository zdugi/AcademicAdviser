package com.academic.adviser.controller;

import com.academic.adviser.dto.BigFiveQuestionDTO;
import com.academic.adviser.dto.BigFiveSurveyAnswersDTO;
import com.academic.adviser.dto.BigFiveSurveyDTO;
import com.academic.adviser.service.BigFiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<Void> submitSurvey(@RequestBody BigFiveSurveyAnswersDTO surveyDTO) {
        // TODO
        System.out.println(surveyDTO.getAnswers().get(0).getScore());
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
}
