package com.academic.adviser.drools.model;

import com.academic.adviser.dto.BigFiveAnswerDTO;
import org.kie.api.definition.type.Role;

import java.util.Date;

@Role(Role.Type.EVENT)
public class BigFiveFlyAnswer {
    private Date factTime;
    private Integer id;
    private Integer score;
    private Integer candidateId;

    public BigFiveFlyAnswer(BigFiveAnswerDTO bigFiveAnswerDTO, Integer candidateId) {
        this.factTime = new Date();
        this.id = bigFiveAnswerDTO.getId();
        this.score = bigFiveAnswerDTO.getScore();
        this.candidateId = candidateId;
    }

    public Date getFactTime() {
        return factTime;
    }

    public Integer getId() {
        return id;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getCandidateId() {
        return candidateId;
    }
}
