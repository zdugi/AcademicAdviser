package com.academic.adviser.runner;

import com.academic.adviser.RunTemplateEngine;
import com.academic.adviser.drools.model.QuestionPair;
import com.academic.adviser.dto.BigFiveTemplateDTO;
import com.academic.adviser.mapper.BigFiveTemplateMapper;
import com.academic.adviser.repository.BigFiveQuestionsRepository;
import com.academic.adviser.repository.QuestionPairRepository;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.drools.template.ObjectDataCompiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class TemplateGenerator implements ApplicationRunner {

    @Autowired
    private BigFiveQuestionsRepository repository;

    @Autowired
    private QuestionPairRepository questionPairRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        InputStream template = RunTemplateEngine.class.getResourceAsStream("/questionTemplate/big-five-questions.drt");

        BigFiveTemplateMapper mapper = new BigFiveTemplateMapper();
        List<BigFiveTemplateDTO> data = mapper.getDTOs(repository.findAll());

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);

        FileOutputStream outputStream = new FileOutputStream("src/main/resources/bigFive/big-five-questions.drl");
        outputStream.write(drl.getBytes());

        //

        template = RunTemplateEngine.class.getResourceAsStream(
                "/questionTemplate/pro-questions.drt");


        List<QuestionPair> pairs = new ArrayList<>();
        for (com.academic.adviser.model.QuestionPair pair : questionPairRepository.findAll())
            pairs.add(
                    new QuestionPair(
                            pair.getId(),
                            pair.getQuestionA().getCareerArea().getId(),
                            pair.getQuestionB().getCareerArea().getId()));

        drl = converter.compile(pairs, template);

        outputStream = new FileOutputStream("src/main/resources/bigFive/pro-questions.drl");
        outputStream.write(drl.getBytes());
    }
}
