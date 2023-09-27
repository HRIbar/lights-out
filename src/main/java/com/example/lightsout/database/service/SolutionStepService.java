package com.example.lightsout.database.service;

import com.example.lightsout.database.entity.Solution;
import com.example.lightsout.database.entity.SolutionStep;
import com.example.lightsout.database.repository.SolutionRepository;
import com.example.lightsout.database.repository.SolutionStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolutionStepService {

    private final SolutionStepRepository solutionStepRepository;
    private final SolutionRepository solutionRepository;

    @Autowired
    public SolutionStepService(SolutionStepRepository solutionStepRepository, SolutionRepository solutionRepository) {
        this.solutionStepRepository = solutionStepRepository;
        this.solutionRepository = solutionRepository;
    }

    public void createSolutionStep(String step, int stepOrder, Long solutionId) {
        SolutionStep solutionStep = new SolutionStep();
        solutionStep.setStep(step);
        solutionStep.setStepOrder(stepOrder);

        Solution solution = solutionRepository.findById(solutionId)
                .orElseThrow(() -> new RuntimeException("Solution not found with ID: " + solutionId));

        solutionStep.setSolution(solution);

        solutionStepRepository.save(solutionStep);
    }

    // Add other methods for managing solution steps as needed
}