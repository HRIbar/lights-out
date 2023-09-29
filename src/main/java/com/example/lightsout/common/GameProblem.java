package com.example.lightsout.common;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class GameProblem {
    private String id;
    private int[][] matrix;
    private int size;

    // Constructor to initialize an instance of GameProblem with a given size
    public GameProblem() {
        Random random = new Random();
        this.size = random.nextInt((8 - 3) + 1) + 3;
        this.id = UUID.randomUUID().toString();
        this.size = size;

        // Create the two-dimensional array with the specified size
        matrix = new int[size][size];

        // Generate random values and initialize the array
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextInt(2); // Generates random values between 0 and 1
            }
        }
    }

    public GameProblem(int size) {
        this.id = UUID.randomUUID().toString();
        this.size = size;

        // Create the two-dimensional array with the specified size
        matrix = new int[size][size];

        // Generate random values and initialize the array
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = random.nextInt(2); // Generates random values between 0 and 1
            }
        }
    }

    public String getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    // Method to print the random 2D array
    public void printArray() {
        System.out.println("Random " + size + "x" + size + " Array:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); // Move to the next row
        }
    }

    // Method to convert the random array to JSON
    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(matrix);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}