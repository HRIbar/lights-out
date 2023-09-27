package com.example.lightsout.database.repository;

import com.example.lightsout.database.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionRepository extends JpaRepository<Solution, Long> {
}
