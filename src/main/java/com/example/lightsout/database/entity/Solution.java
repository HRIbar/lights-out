package com.example.lightsout.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "solution")
public class Solution {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "solutionSteps")
    private int solutionSteps;

    @OneToMany(mappedBy = "solution", cascade = CascadeType.ALL)
    private List<SolutionStep> solutionStepsList;

    @OneToOne
    @JoinColumn(name = "problemId", referencedColumnName = "id")
    private Problem problem;

    @Column(name = "solutionMatrix")
    private String solutionMatrix;

    public Solution() {

    }

    public Solution(String solutionMatrix, Problem problem) {
        this.id = UUID.randomUUID().toString();
        this.solutionStepsList = new ArrayList<>();
        this.solutionSteps = 0;
        this.solutionMatrix = solutionMatrix;
        this.problem = problem;
    }

    public void setSolutionSteps(int solutionSteps) {
        this.solutionSteps = solutionSteps;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public String getSolutionMatrix() {
        return this.solutionMatrix;
    }

    public String getId() {
        return this.id;
    }

    public Problem getProblem() {
        return (this.problem != null) ? this.problem : null;
    }

    public int getSolutionSteps() {
        return this.solutionSteps;
    }
}