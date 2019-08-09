package com.jihan.algorithm.剑指offer;

import java.util.HashMap;

/**
 * Created by Jihan on 2019/8/8
 *
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class 数组中超过出现一半的数字 {
    public int MoreThanHalfNum_Solution(int[] array) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {

            int key = array[i];

            if (!map.containsKey(key)) {
                map.put(key, 0);
            }

            int count = map.get(key);
            map.put(key, ++count);
            if (count > array.length / 2) {
                return key;
            }

        }

        return 0;
    }
}
