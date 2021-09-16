package com.jihan.algorithm.剑指offer;

/**
 * Created by Jihan on 2019/8/2
 *
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */

public class 调整数组奇偶数 {
    public class Solution {
        public void reOrderArray(int[] array) {
            int leftCount = 0;
            int cur = 0;
            while (cur < array.length) {
                if ((array[cur] & 1) == 1) {
                    leftCount++;
                }
                cur++;
            }

            int[] copy = array.clone();
            int right = leftCount;
            int left = 0;
            for (int j = 0; j < copy.length; j++) {
                if ((copy[j] & 1) != 1) {
                    array[right++] = copy[j];
                } else {
                    array[left++] = copy[j];
                }
            }
        }
    }
}
