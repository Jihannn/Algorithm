package com.jihan.algorithm.剑指offer;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Jihan
 * @date 2019/8/31
 *
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}。
 */
public class 滑动窗口的最大值 {
    public class Solution {
        public ArrayList<Integer> maxInWindows(int[] num, int size) {
            ArrayList<Integer> returnList = new ArrayList<>();
            if (num == null || num.length == 0 || size <= 0 || num.length < size) {
                return returnList;
            }
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < num.length; i++) {
                while (!list.isEmpty() && num[list.peekLast()] < num[i]) {
                    list.pollLast();
                }
                list.addLast(i);

                if (i - list.peekFirst() == size) {
                    list.pollFirst();
                }

                if (i + 1 >= size) {
                    returnList.add(num[list.peekFirst()]);
                }
            }
            return returnList;
        }
    }
}
