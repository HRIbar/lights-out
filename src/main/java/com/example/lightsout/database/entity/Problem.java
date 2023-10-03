package com.example.lightsout.database.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "problem")
public class Problem {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "matrix", columnDefinition = "TEXT")
    private String matrix;

    @Column(name = "size")
    private int size;

    @OneToOne(mappedBy = "problem", cascade = CascadeType.ALL)
    private Solution solution;

    public Problem() { }

    public Problem(String id, String matrix, int size) {
        this.id = id;
        this.matrix = matrix;
        this.size = size;
    }

    public String getId() {
        return this.id;
    }

    public String getMatrix() {
        return this.matrix;
    }

    public int getSize() {
        return this.size;
    }


}