package com.example.lightsout.database.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "solution")
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "solutionSteps")
    private String solutionSteps;

    @OneToMany(mappedBy = "solution", cascade = CascadeType.ALL)
    private List<SolutionStep> solutionStepsList;

    @OneToOne
    @JoinColumn(name = "problemId", referencedColumnName = "id")
    private Problem problem;

    @Column(name = "solvedMatrix")
    private String solvedMatrix;

    public void setSolutionSteps(String solutionSteps) {
        this.solutionSteps = solutionSteps;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    // Constructors, getters, setters, etc.
}
