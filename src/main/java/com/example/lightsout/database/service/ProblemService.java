package com.example.lightsout.database.service;

import com.example.lightsout.common.GameProblem;
import com.example.lightsout.database.entity.Problem;
import com.example.lightsout.database.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public void createProblem(GameProblem gameProblem) {
        String matrixJson = gameProblem.toJson();
        Problem problem = new Problem(gameProblem.getId(), matrixJson, gameProblem.getSize());
        problemRepository.save(problem);
    }

    public Optional<Problem> getProblemById(String id) {
        return problemRepository.findById(id);
    }

    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

}