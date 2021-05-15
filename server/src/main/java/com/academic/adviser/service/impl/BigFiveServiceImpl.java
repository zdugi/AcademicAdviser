package com.academic.adviser.service.impl;

import com.academic.adviser.RunTemplateEngine;
import com.academic.adviser.dto.BigFiveAnswerDTO;
import com.academic.adviser.dto.BigFiveQuestionDTO;
import com.academic.adviser.dto.BigFiveSurveyAnswersDTO;
import com.academic.adviser.dto.BigFiveSurveyDTO;
import com.academic.adviser.mapper.BigFiveQuestionMapper;
import com.academic.adviser.model.BigFiveQuestion;
import com.academic.adviser.model.BigFiveResults;
import com.academic.adviser.repository.BigFiveQuestionsRepository;
import com.academic.adviser.service.BigFiveService;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class BigFiveServiceImpl implements BigFiveService {
    @Autowired
    private BigFiveQuestionsRepository bigFiveQuestionsRepository;

    @Override
    public BigFiveSurveyDTO getBigFiveQuestions() {
        List<BigFiveQuestion> questions = bigFiveQuestionsRepository.findAll();
        ArrayList<BigFiveQuestionDTO> questionDTOS = new ArrayList<>();

        for (BigFiveQuestion question : questions) {
            BigFiveQuestionMapper mapper = new BigFiveQuestionMapper(question);
            questionDTOS.add(mapper.getDTO());
        }

        return new BigFiveSurveyDTO(questionDTOS);
    }

    @Override
    public void submitBigFiveSurvey(BigFiveSurveyAnswersDTO answers) {
        BigFiveResults bigFiveResults = new BigFiveResults();
        InputStream template = RunTemplateEngine.class.getResourceAsStream("/bigFive/big-five-questions.drl");
        try {
            KieSession session = createKieSessionFromDRL(new String(template.readAllBytes(), StandardCharsets.UTF_8));
            for(BigFiveAnswerDTO answerDTO : answers.getAnswers())
                session.insert(answerDTO);
            session.insert(bigFiveResults);

            session.fireAllRules();

            System.out.println(bigFiveResults.getAgreeableness());
            System.out.println(bigFiveResults.getConscientiousness());
            System.out.println(bigFiveResults.getExtroversion());
            System.out.println(bigFiveResults.getNeuroticism());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private KieSession createKieSessionFromDRL(String drl){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);

        Results results = kieHelper.verify();

        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }

            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }

        return kieHelper.build().newKieSession();
    }
}
