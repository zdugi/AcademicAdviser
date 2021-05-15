package com.academic.adviser.mapper;

import com.academic.adviser.dto.BigFiveTemplateDTO;
import com.academic.adviser.model.BigFiveQuestion;

import java.util.List;
import java.util.stream.Collectors;

public class BigFiveTemplateMapper {
    public BigFiveTemplateDTO getDTO(BigFiveQuestion bigFiveQuestion) {
        String trait = bigFiveQuestion.getTrait().toString();
        trait = trait.substring(0, 1).toUpperCase() + trait.substring(1).toLowerCase();
        return new BigFiveTemplateDTO(
                bigFiveQuestion.getId(),
                trait,
                bigFiveQuestion.getOperation().toString().toLowerCase()
        );
    }

    public List<BigFiveTemplateDTO> getDTOs(List<BigFiveQuestion> bigFiveQuestions) {
        return bigFiveQuestions.stream().map(this::getDTO).collect(Collectors.toList());
    }
}
