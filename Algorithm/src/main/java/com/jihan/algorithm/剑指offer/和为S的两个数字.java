package com.jihan.algorithm.剑指offer;

import java.util.ArrayList;

/**
 * @author Jihan
 * @date 2019/8/16
 *
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，
 * 如果有多对数字的和等于S，输出两个数的乘积最小的。
 */
public class 和为S的两个数字 {
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> list = new ArrayList<>();

        int low = 0;
        int high = array.length - 1;
        while (low < high) {
            int count = array[low] + array[high];
            if (count == sum) {
                list.add(array[low]);
                list.add(array[high]);
                return list;
            } else if (count > sum) {
                --high;
            } else {
                ++low;
            }
        }

        return list;
    }
}
