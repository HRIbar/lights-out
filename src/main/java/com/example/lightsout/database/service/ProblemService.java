package com.example.lightsout.database.service;

import com.example.lightsout.database.entity.Problem;
import com.example.lightsout.database.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public void createProblem(String matrixData) {
        Problem problem = new Problem();
        problem.setMatrix(matrixData);
        problemRepository.save(problem);
    }
}