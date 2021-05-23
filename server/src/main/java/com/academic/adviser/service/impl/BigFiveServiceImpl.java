package com.academic.adviser.service.impl;

import com.academic.adviser.RunTemplateEngine;
import com.academic.adviser.drools.model.CareerTest;
import com.academic.adviser.dto.BigFiveAnswerDTO;
import com.academic.adviser.dto.BigFiveQuestionDTO;
import com.academic.adviser.dto.BigFiveSurveyAnswersDTO;
import com.academic.adviser.dto.BigFiveSurveyDTO;
import com.academic.adviser.mapper.BigFiveQuestionMapper;
import com.academic.adviser.model.BigFiveQuestion;
import com.academic.adviser.model.BigFiveResults;
import com.academic.adviser.model.CareerArea;
import com.academic.adviser.model.QuestionPair;
import com.academic.adviser.repository.BigFiveQuestionsRepository;
import com.academic.adviser.repository.CareerAreaRepository;
import com.academic.adviser.repository.QuestionPairRepository;
import com.academic.adviser.service.BigFiveService;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BigFiveServiceImpl implements BigFiveService {
    @Autowired
    private BigFiveQuestionsRepository bigFiveQuestionsRepository;

    @Autowired
    private CareerAreaRepository careerAreaRepository;

    @Autowired
    private QuestionPairRepository questionPairRepository;

    private KieContainer kContainer;
    public BigFiveServiceImpl() {
        KieServices ks = KieServices.Factory.get();
        kContainer = ks
                .newKieContainer(ks.newReleaseId(
                        "org.adviserkjar", "server-kjar", "1.0-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        kScanner.start(10_000);
    }

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
    public void submitBigFiveSurvey(BigFiveSurveyAnswersDTO answers) throws IOException {
        BigFiveResults bigFiveResults = new BigFiveResults();
        //InputStream template = RunTemplateEngine.class.getResourceAsStream("/bigFive/pro-questions.drl");
        //KieSession session = createKieSessionFromDRL(new String(template.readAllBytes(), StandardCharsets.UTF_8));
        KieSession session = kContainer.newKieSession("ksession-bigfive-rules");

        CareerArea areaA = careerAreaRepository.findAll().get(0);
        CareerArea areaB = careerAreaRepository.findAll().get(1);
        CareerTest test = new CareerTest();

        session.insert(test);

        session.insert(areaA);
        session.insert(areaB);

        session.fireAllRules();

        //System.out.println("Added: " + test.getQuestionPairs().size());
        //System.out.println("Done. " + areaA.getId() + " " + areaB.getId());
        for (Integer pairId : test.getQuestionPairs()) {
            Optional<QuestionPair> pair = questionPairRepository.findById(pairId);

            System.out.println(pair.get().getQuestionA().getText());
            System.out.println(pair.get().getQuestionB().getText());
            System.out.println();
        }

        System.out.println("Total pairs: " + test.getQuestionPairs().size());


        /*session.insert(bigFiveResults);
        for(BigFiveAnswerDTO answerDTO : answers.getAnswers()) {
            session.insert(answerDTO);
        }

        session.fireAllRules();

        System.out.println(bigFiveResults.getAgreeableness());
        System.out.println(bigFiveResults.getConscientiousness());
        System.out.println(bigFiveResults.getExtroversion());
        System.out.println(bigFiveResults.getNeuroticism());
        System.out.println();*/



        //System.out.println("pass bigFive");

        // Group 1
        //InputStream template_group2 =
        //        RunTemplateEngine.class.getResourceAsStream("/bigFive/big-five-traits-to-pro-questions.drl");
        //try {
        //    KieSession session = createKieSessionFromDRL(new String(template_group2.readAllBytes(), StandardCharsets.UTF_8));
         //   session.insert(bigFiveResults);
        //    session.fireAllRules();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
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
