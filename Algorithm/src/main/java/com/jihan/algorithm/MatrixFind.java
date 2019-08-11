package com.jihan.algorithm;

/**
 * Created by Jihan on 2019/7/15
 */
public class MatrixFind {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}};
        System.out.println(findMatrixNum(matrix,-4));
    }

    private static boolean findMatrixNum(int[][] matrix, int num) {
        int tR = 0;
        int tC = matrix[0].length - 1;
        while(tR < matrix.length && tC >= 0){
            if (matrix[tR][tC] > num) {
                tC--;
            } else if(matrix[tR][tC] < num){
                tR++;
            }else{
                return true;
            }
        }
        return false;
    }
}
