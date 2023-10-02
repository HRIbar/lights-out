package com.example.lightsout.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ArrayConversionService {

    public int[][] convertStringToArray(String arrayString) throws Exception {
        // Split the string into rows
        String[] rows = arrayString.split("\\],\\[");

        // Determine the number of rows and columns
        int numRows = rows.length;
        int numCols = rows[0].split(",").length;

        // Create the result array
        int[][] result = new int[numRows][numCols];

        // Populate the result array
        for (int i = 0; i < numRows; i++) {
            // Remove leading and trailing brackets from each row string
            String row = rows[i].replaceAll("\\[", "").replaceAll("\\]","");
            String[] values = row.split(",");
            for (int j = 0; j < numCols; j++) {
                result[i][j] = Integer.parseInt(values[j]);
            }
        }

        return result;
    }


    public String convertArrayToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for (int i = 0; i < matrix.length; i++) {
            sb.append("[");
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(matrix[i][j]);
                if (j < matrix[i].length - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
            if (i < matrix.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");

        return sb.toString();
    }
}
