package com.academic.adviser.repository;

import com.academic.adviser.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    Candidate findByUsername(String username);
}
