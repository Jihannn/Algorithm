package com.jihan.algorithm.剑指offer;

import java.util.HashMap;

/**
 * @author Jihan
 * @date 2019/8/15
 *
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。
 */
public class 数组中只出现一次的数字 {
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if (array == null) {
            return;
        }
        if (array.length == 2) {
            num1[0] = array[0];
            num2[0] = array[1];
            return;
        }

        int length = array.length;
        int temp = array[0];
        for (int i = 1; i < length; i++) {
            temp ^= array[i];
        }
        int index = checkBinaryIndex(temp);
        for (int j = 0; j < length; j++) {
            if (checkIndex(array[j], index)) {
                num1[0] ^= array[j];
            } else {
                num2[0] ^= array[j];
            }
        }
    }

    private int checkBinaryIndex(int temp) {
        int index = 0;
        while (index <= 32 && ((temp & 1) != 1)) {
            temp >>= 1;
            index++;
        }
        return index;
    }

    private boolean checkIndex(int array, int index) {
        return (array >> index & 1) == 1;
    }

    //额外空间HashMap
    public void FindNumsAppearOnce2(int[] array, int num1[], int num2[]) {
        if (array == null) {
            return;
        }
        if (array.length == 2) {
            num1[0] = array[0];
            num2[0] = array[1];
            return;
        }

        int length = array.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int index;
        for (int i = 0; i < length; i++) {
            index = array[i];
            if (map.containsKey(index)) {
                map.put(index, 2);
            } else {
                map.put(index, 1);
            }
        }

        index = 0;
        boolean first = true;
        while (index < length) {
            if (map.get(array[index]) == 1) {
                if (first) {
                    num1[0] = array[index];
                    first = false;
                } else {
                    num2[0] = array[index];
                }
            }
            ++index;
        }
    }
}
