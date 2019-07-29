package com.jihan.algorithm.剑指offer;

/**
 * Created by Jihan on 2019/7/29
 *
 * 题目描述
 * 在一个二维数组中（每个一维数组的长度相同），
 * 每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */

public class 二维数组中的查找 {

    //根据给出条件，右上往左下遍历，target大则往下，小则往左。

    public boolean Find(int target, int[][] array) {
        if (array == null) {
            return false;
        }
        int rowCur = 0;
        int colCur = array[0].length - 1;
        while (rowCur < array.length && colCur > -1) {
            int num = array[rowCur][colCur];
            if (num > target) {
                colCur--;
            } else if (num < target) {
                rowCur++;
            } else {
                return true;
            }
        }
        return false;
    }
}
