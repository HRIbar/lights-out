package com.example.lightsout.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LightsOutSolverService {

    public int[][] solve(int[][] problem) {
        int n = problem.length;
        int m = problem[0].length;
        int[][] matrix = new int[n * m][n * m];
        int[] vector = new int[n * m];

        // Build the matrix and vector for the linear system
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int row = i * m + j;
                vector[row] = problem[i][j];
                for (int di = -1; di <= 1; di++) {
                    for (int dj = -1; dj <= 1; dj++) {
                        int ni = i + di, nj = j + dj;
                        if (ni >= 0 && ni < n && nj >= 0 && nj < m && (di == 0 || dj == 0)) {
                            int col = ni * m + nj;
                            matrix[row][col] = 1;
                        }
                    }
                }
            }
        }

        // Solve the system using Gaussian elimination
        int[] solutionVector = GaussianEliminationGF2.solve(matrix, vector);

        // Convert the solution vector back to a 2D array
        int[][] solution = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int row = i * m + j;
                solution[i][j] = solutionVector[row];
            }
        }

        return solution;
    }
}


