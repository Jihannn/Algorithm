package com.jihan.algorithm;

/**
 * Created by Jihan on 2019/7/12
 */
public class MatrixRotatePrint {

    public static void MatrixRotate(int[][] matrix) {

        int tR = 0;
        int tC = 0;
        int bR = matrix.length - 1;
        int bC = matrix[0].length - 1;

        while (tR < bR) {
            squareRotate(matrix, tR, tC, bR, bC);
            rotatePrintMatrix(matrix, tR++, tC++, bR--, bC--);
        }
        printMatrix(matrix);
    }

    private static void squareRotate(int[][] matrix, int tR, int tC, int bR, int bC) {
        int temp;
        int diff = bC - tC;
        for (int i = 0; i < diff; i++) {
            temp = matrix[tR][tC + i];
            matrix[tR][tC + i] = matrix[bR - i][tC];
            matrix[bR - i][tC] = matrix[bR][bC - i];
            matrix[bR][bC - i] = matrix[tR + i][bC];
            matrix[tR + i][bC] = temp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        System.out.println();
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void rotatePrintMatrix(int[][] matrix, int tR, int tC, int bR, int bC) {
        if (tR == bR) {
            for (int i = tC; i <= bC; i++) {
                System.out.print(matrix[tR][i] + ",");
            }
        } else if (tC == bC) {
            for (int i = tR; i < bR; i++) {
                System.out.print(matrix[i][tC] + ",");
            }
        } else {
            int curR = tR;
            int curC = tC;
            while (curC < bC) {
                System.out.print(matrix[curR][curC] + ",");
                curC++;
            }
            while (curR < bR) {
                System.out.print(matrix[curR][curC] + ",");
                curR++;
            }
            while (curC > tC) {
                System.out.print(matrix[curR][curC] + ",");
                curC--;
            }
            while (curR > tR) {
                System.out.print(matrix[curR][curC] + ",");
                curR--;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printMatrix(matrix);
        MatrixRotate(matrix);
    }
}
