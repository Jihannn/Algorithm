package com.jihan.algorithm.剑指offer;

/**
 * Created by Jihan on 2019/8/8
 *
 * 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
 * 给一个数组，返回它的最大连续子序列的和。
 */
public class 连续子数组的最大和 {

    public int FindGreatestSumOfSubArray(int[] array) {
        int count = array[0];
        int max = array[0];
        for(int i = 1; i < array.length; i++){
            count = count + array[i] > array[i] ? count + array[i] : array[i];
            max = count > max ? count : max;
        }
        return max;
    }

}
