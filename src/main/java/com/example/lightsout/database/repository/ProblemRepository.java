package com.example.lightsout.database.repository;

import com.example.lightsout.database.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

    Optional<Problem> findById(String id);

    List<Problem> findAll();
}