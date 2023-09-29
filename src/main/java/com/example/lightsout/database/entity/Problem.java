package com.example.lightsout.database.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "problem")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "problem_id", unique = true, nullable = false)
    private String problemId;

    @Column(name = "matrix", columnDefinition = "TEXT")
    private String matrix;

    @Column(name = "size")
    private int size;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL)
    private List<Solution> solutions;

    public Problem() { }

    public Problem(String problemId, String matrix, int size) {
        this.problemId = problemId;
        this.matrix = matrix;
        this.size = size;
    }

    // Getter and Setter methods...
}