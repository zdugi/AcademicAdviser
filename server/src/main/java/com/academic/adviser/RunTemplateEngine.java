package com.academic.adviser;

import com.academic.adviser.constants.BigFiveTrait;
import com.academic.adviser.constants.Operation;
import com.academic.adviser.dto.BigFiveTemplateDTO;
import com.academic.adviser.mapper.BigFiveTemplateMapper;
import com.academic.adviser.model.BigFiveQuestion;
import org.drools.template.ObjectDataCompiler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RunTemplateEngine {
    public static void main(String[] args) throws IOException {
        InputStream template = RunTemplateEngine.class.getResourceAsStream("/questionTemplate/big-five-questions.drt");

        List<BigFiveTemplateDTO> data = new ArrayList<>();
        BigFiveTemplateMapper mapper = new BigFiveTemplateMapper();

        data.add(mapper.getDTO(new BigFiveQuestion(1, "", BigFiveTrait.CONSCIENTIOUSNESS, Operation.ADD)));
        data.add(mapper.getDTO(new BigFiveQuestion(2, "", BigFiveTrait.AGREEABLENESS, Operation.ADD)));
        data.add(mapper.getDTO(new BigFiveQuestion(3, "", BigFiveTrait.NEUROTICISM, Operation.ADD)));
        data.add(mapper.getDTO(new BigFiveQuestion(4, "", BigFiveTrait.CONSCIENTIOUSNESS, Operation.SUB)));

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);

        FileOutputStream outputStream = new FileOutputStream("D:\\VIII_semestar\\SBZ\\projekat\\AcademicAdviser\\server\\src\\main\\resources\\bigFive\\big-five-questions.drl");
        outputStream.write(drl.getBytes());
    }
}
