package com.example.lightsout.service;

public class GaussianEliminationService {

    public static int[] solve(int[][] matrix, int[] vector) {
        int n = matrix.length;
        for (int row = 0; row < n; row++) {
            // Find a row with a nonzero entry and swap
            int swapRow = row;
            while (swapRow < n && matrix[swapRow][row] == 0) {
                swapRow++;
            }
            if (swapRow == n) {
                continue;  // No solution if all zero in this column
            }
            swap(matrix, row, swapRow);
            swap(vector, row, swapRow);

            // Eliminate below
            for (int i = row + 1; i < n; i++) {
                if (matrix[i][row] == 1) {
                    xorRows(matrix, i, row);
                    vector[i] ^= vector[row];
                }
            }
        }

        // Back substitution
        int[] solution = new int[n];
        for (int row = n - 1; row >= 0; row--) {
            int sum = vector[row];
            for (int col = row + 1; col < n; col++) {
                sum ^= (matrix[row][col] * solution[col]);
            }
            solution[row] = sum;
        }

        return solution;
    }

    private static void swap(int[][] matrix, int row1, int row2) {
        int[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }

    private static void swap(int[] vector, int row1, int row2) {
        int temp = vector[row1];
        vector[row1] = vector[row2];
        vector[row2] = temp;
    }

    private static void xorRows(int[][] matrix, int row1, int row2) {
        int n = matrix[0].length;
        for (int j = 0; j < n; j++) {
            matrix[row1][j] ^= matrix[row2][j];
        }
    }
}