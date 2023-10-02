package com.example.lightsout.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LightsOutSolverService {

    private static final int[] dx = {0, 0, 1, 0, -1};
    private static final int[] dy = {0, 1, 0, -1, 0};

    public int[][] solve(int[][] problem) {
        Set<String> visited = new HashSet<>();
        Queue<int[][]> boardQueue = new LinkedList<>();
        Queue<int[][]> toggleQueue = new LinkedList<>();
        int n = problem.length;
        int m = problem[0].length;

        boardQueue.offer(problem);
        toggleQueue.offer(new int[n][m]);  // Initialize toggle matrix with zeros
        visited.add(matrixToString(problem));

        while (!boardQueue.isEmpty()) {
            int[][] currentBoard = boardQueue.poll();
            int[][] currentToggle = toggleQueue.poll();

            if (isSolved(currentBoard)) {
                return currentToggle;  // Return the toggle matrix
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int[][] nextBoard = toggle(currentBoard, i, j);
                    int[][] nextToggle = copyMatrix(currentToggle);
                    nextToggle[i][j] ^= 1;  // Toggle the corresponding field in the toggle matrix
                    String nextStr = matrixToString(nextBoard);
                    if (!visited.contains(nextStr)) {
                        visited.add(nextStr);
                        boardQueue.offer(nextBoard);
                        toggleQueue.offer(nextToggle);
                    }
                }
            }
        }

        return new int[0][0];  // Return null if no solution is found
    }

    private int[][] toggle(int[][] board, int x, int y) {
        int n = board.length;
        int m = board[0].length;
        int[][] newBoard = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, m);
        }
        for (int d = 0; d < 5; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                newBoard[nx][ny] ^= 1;
            }
        }
        return newBoard;
    }

    private boolean isSolved(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private String matrixToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            for (int cell : row) {
                sb.append(cell);
            }
        }
        return sb.toString();
    }
    private int[][] copyMatrix(int[][] original) {
        int n = original.length;
        int m = original[0].length;
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, m);
        }
        return copy;
    }
}

