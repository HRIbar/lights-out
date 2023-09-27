package com.example.lightsout.database.repository;

import com.example.lightsout.database.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
}
