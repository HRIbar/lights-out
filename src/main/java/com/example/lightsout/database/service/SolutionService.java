package com.example.lightsout.database.service;

import com.example.lightsout.database.entity.Problem;
import com.example.lightsout.database.entity.Solution;
import com.example.lightsout.database.repository.ProblemRepository;
import com.example.lightsout.database.repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolutionService {

    private final SolutionRepository solutionRepository;
    private final ProblemRepository problemRepository;

    @Autowired
    public SolutionService(SolutionRepository solutionRepository, ProblemRepository problemRepository) {
        this.solutionRepository = solutionRepository;
        this.problemRepository = problemRepository;
    }

    public void createSolution(String solutionMatrix, Problem problem) {
        Solution solution = new Solution(solutionMatrix, problem);
        solutionRepository.save(solution);
    }

    public Optional<Solution> getByProblemId(String problemKey) {
        return solutionRepository.getByProblemId(problemKey);
    }

    public List<Solution> getAllSolutions() {
        return solutionRepository.findAll();
    }

    public Solution updateSolution(String solutionId, Solution updatedSolution) {
        return solutionRepository.getById(solutionId)
                .map(solution -> {
                    solution.setSolutionSteps(updatedSolution.getSolutionSteps());
                    return solutionRepository.save(solution);
                })
                .orElseThrow(() -> new IllegalArgumentException("Solution with id " + solutionId + " not found"));
    }
}
