package com.example.lightsout.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "solution_step")
public class SolutionStep {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "solutionKey", referencedColumnName = "id")
    private Solution solution;

    @Column(name = "step", columnDefinition = "TEXT")
    private String step;

    @Column(name = "stepOrder")
    private int stepOrder;

    public void setStep(String step) {
        this.step = step;
    }

    public void setStepOrder(int stepOrder) {
        this.stepOrder = stepOrder;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    // Constructors, getters, setters, etc.
}