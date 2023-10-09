package com.example.lightsout.database.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "solution_step")
public class SolutionStep {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "solutionKey", referencedColumnName = "id")
    private Solution solution;

    @Column(name = "stepIndex", columnDefinition = "TEXT")
    private String stepIndex;

    @Column(name = "stepOrder")
    private int stepOrder;

    public SolutionStep() {

    }

    public SolutionStep(Solution solution, String stepIndex, int stepOrder) {
        this.id = UUID.randomUUID().toString();
        this.solution = solution;
        this.stepIndex = stepIndex;
        this.stepOrder = stepOrder;

    }

    public void setStepIndex(String stepIndex) {
        this.stepIndex = stepIndex;
    }

    public void setStepOrder(int stepOrder) {
        this.stepOrder = stepOrder;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }
}