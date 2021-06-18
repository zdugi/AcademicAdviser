package com.academic.adviser.mapper;

import com.academic.adviser.dto.ProfileDTO;
import com.academic.adviser.model.Candidate;

public class ProfileMapper {
    public ProfileDTO toDto(Candidate candidate) {
        return new ProfileDTO(
                candidate.getId(),
                candidate.getEmailAddress(),
                candidate.getFirstName(),
                candidate.getLastName(),
                candidate.getGrade()
        );
    }
}
