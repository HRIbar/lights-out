package com.example.lightsout.database.service;

import com.example.lightsout.database.entity.Solution;
import com.example.lightsout.database.entity.SolutionStep;
import com.example.lightsout.database.repository.SolutionRepository;
import com.example.lightsout.database.repository.SolutionStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.lightsout.service.ArrayConversionService;

@Service
public class SolutionStepService {

    private final SolutionStepRepository solutionStepRepository;
    private final SolutionRepository solutionRepository;

    @Autowired
    private ArrayConversionService arrayConversionService;

    @Autowired
    public SolutionStepService(SolutionStepRepository solutionStepRepository, SolutionRepository solutionRepository) {
        this.solutionStepRepository = solutionStepRepository;
        this.solutionRepository = solutionRepository;
    }

    public void createSolutionSteps(Solution solution) throws Exception {
        int[][] solutionArray = arrayConversionService.convertStringToArray(solution.getSolutionMatrix());

        //iterate throus solutionArray and create solutionStep everytime that there is '1' in array
        int k = 0;
        for (int i = 0; i < solutionArray.length; i++) {
            for (int j = 0; j < solutionArray.length; j++) {
                if(solutionArray[i][j] == 1) {
                    k++;
                    createSolutionStep(solution,i +","+ j, k);
                }
            }
        }
    }

    public void createSolutionStep(Solution solution, String stepIndex, int stepOrder) {
        SolutionStep solutionStep = new SolutionStep(solution,stepIndex,stepOrder);
        solutionStepRepository.save(solutionStep);
    }



    // Add other methods for managing solution steps as needed
}