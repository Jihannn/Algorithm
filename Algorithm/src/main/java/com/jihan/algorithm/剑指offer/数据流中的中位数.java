package com.jihan.algorithm.剑指offer;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Jihan
 * @date 2019/8/31
 *
 * 如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 */
public class 数据流中的中位数 {
    public class Solution {
        int count;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(11, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        public void Insert(Integer num) {
            ++count;
            if ((count & 1) == 0) {
                if (!maxHeap.isEmpty() && num < maxHeap.peek()) {
                    maxHeap.offer(num);
                    num = maxHeap.poll();
                }
                minHeap.offer(num);
            } else {
                if (!minHeap.isEmpty() && num > minHeap.peek()) {
                    minHeap.offer(num);
                    num = minHeap.poll();
                }
                maxHeap.offer(num);
            }
        }

        public Double GetMedian() {
            if (count == 0) {
                return null;
            }
            double result;
            if ((count & 1) == 1) {
                result = maxHeap.peek();
            } else {
                result = (maxHeap.peek() + minHeap.peek()) / 2.0;
            }
            return result;
        }
    }
}
