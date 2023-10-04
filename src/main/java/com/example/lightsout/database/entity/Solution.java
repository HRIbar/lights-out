package com.example.lightsout.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "solution")
public class Solution {

    public Solution(String solutionMatrix, Problem problem) {
        this.id = UUID.randomUUID().toString();
        this.solutionStepsList = new ArrayList<>();
        this.solutionSteps = "";
        this.solutionMatrix = solutionMatrix;
        this.problem = problem;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "solutionSteps")
    private String solutionSteps;

    @OneToMany(mappedBy = "solution", cascade = CascadeType.ALL)
    private List<SolutionStep> solutionStepsList;

    @OneToOne
    @JoinColumn(name = "problemId", referencedColumnName = "id")
    private Problem problem;

    @Column(name = "solutionMatrix")
    private String solutionMatrix;

    public Solution() {

    }

    public void setSolutionSteps(String solutionSteps) {
        this.solutionSteps = solutionSteps;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public String getSolutionMatrix(){
        return this.solutionMatrix;
    }

    // Constructors, getters, setters, etc.
}
