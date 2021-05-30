package com.academic.adviser.mapper;

import com.academic.adviser.drools.model.BigFiveTemplate;
import com.academic.adviser.model.BigFiveQuestion;

import java.util.List;
import java.util.stream.Collectors;

public class BigFiveTemplateMapper {
    public BigFiveTemplate toTemplate(BigFiveQuestion bigFiveQuestion) {
        String trait = bigFiveQuestion.getTrait().toString();
        trait = trait.substring(0, 1).toUpperCase() + trait.substring(1).toLowerCase();
        return new BigFiveTemplate(
                bigFiveQuestion.getId(),
                trait,
                bigFiveQuestion.getOperation().toString().toLowerCase()
        );
    }

    public List<BigFiveTemplate> toTemplates(List<BigFiveQuestion> bigFiveQuestions) {
        return bigFiveQuestions.stream().map(this::toTemplate).collect(Collectors.toList());
    }
}
