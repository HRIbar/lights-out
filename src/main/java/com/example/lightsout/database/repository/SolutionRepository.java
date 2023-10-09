package com.example.lightsout.database.repository;

import com.example.lightsout.database.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SolutionRepository extends JpaRepository<Solution, Long> {
    Optional<Solution> getByProblemId(String problemId);

    Optional<Solution> getById(String solutionId);
}