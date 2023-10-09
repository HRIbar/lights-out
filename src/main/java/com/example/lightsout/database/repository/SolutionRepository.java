package com.example.lightsout.database.repository;

import com.example.lightsout.database.entity.Problem;
import com.example.lightsout.database.entity.Solution;
import com.example.lightsout.database.entity.SolutionStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SolutionRepository extends JpaRepository<Solution, Long> {
    Optional<Solution> getByProblemId(String problemId);

    Optional<Solution> getById(String solutionId);

}
