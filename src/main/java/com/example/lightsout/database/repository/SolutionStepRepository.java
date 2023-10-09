package com.example.lightsout.database.repository;

import com.example.lightsout.database.entity.SolutionStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolutionStepRepository extends JpaRepository<SolutionStep, Long> {

    List<SolutionStep> findBySolution_Id(String solutionId);
}