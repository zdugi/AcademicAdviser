package com.academic.adviser.repository;

import com.academic.adviser.model.AcademicLife;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicLifeRepository extends JpaRepository<AcademicLife, Integer> {
}
