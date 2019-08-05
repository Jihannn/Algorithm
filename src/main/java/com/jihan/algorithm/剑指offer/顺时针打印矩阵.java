package com.jihan.algorithm.剑指offer;

import java.util.ArrayList;
/**
 * Created by Jihan on 2019/8/4
 *
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵：
 *  1    2   3   4
 *  5    6   7   8
 *  9   10  11  12
 * 13   14  15  16
 * 则依次打印出数字
 * 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10
 */
public class 顺时针打印矩阵 {
    public ArrayList<Integer> printMatrix(int [][] matrix) {

        int tR = 0;
        int tC = 0;
        int bR = matrix.length-1;
        int bC = matrix[bR].length-1;
        ArrayList<Integer> list = new ArrayList<>();

        return process(tR,tC,bR,bC,matrix,list);
    }

    public ArrayList<Integer> process(int tR,int tC,int bR,int bC,int[][] matrix,ArrayList list){
        if(tR > bR || tC > bC){
            return null;
        }

        if(tR == bR){
            while(tC <= bC){
                list.add(matrix[tR][tC++]);
            }
            return list;
        }
        if(tC == bC){
            while(tR <= bR){
                list.add(matrix[tR++][tC]);
            }
            return list;
        }

        int rowCur = tR;
        int colCur = tC;

        while(colCur < bC){
            list.add(matrix[rowCur][colCur++]);
        }
        while(rowCur < bR){
            list.add(matrix[rowCur++][colCur]);
        }
        while(colCur > tC){
            list.add(matrix[rowCur][colCur--]);
        }
        while(rowCur > tR){
            list.add(matrix[rowCur--][colCur]);
        }

        process(++tR,++tC,--bR,--bC,matrix,list);

        return list;
    }
}
