package com.jihan.algorithm.剑指offer;

import java.util.ArrayList;

/**
 * @author Jihan
 * @date 2019/8/16
 *
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 */
public class 和为S的连续正数序列 {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        int low = 1;
        int high = 2;
        while (low < high) {
            int count = (high + low) * (high - low + 1) / 2;
            if (count == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = low; i <= high; i++) {
                    list.add(i);
                }
                lists.add(list);
                ++low;
            } else if (count < sum) {
                ++high;
            } else {
                ++low;
            }
        }
        return lists;
    }
}
