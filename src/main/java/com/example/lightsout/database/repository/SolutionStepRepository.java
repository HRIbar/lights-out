package com.example.lightsout.database.repository;

import com.example.lightsout.database.entity.SolutionStep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionStepRepository extends JpaRepository<SolutionStep, Long> {
}