package com.example.lightsout.database.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "problem")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "matrix")
    private String matrix;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL)
    private List<Solution> solutions;

    public void setMatrix(String matrixData) {
        this.matrix = matrixData;
    }

    // Constructors, getters, setters, etc.
}