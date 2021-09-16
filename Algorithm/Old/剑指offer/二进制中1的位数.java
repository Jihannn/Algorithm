package com.jihan.algorithm.剑指offer;

/**
 * Created by Jihan on 2019/8/2
 *
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class 二进制中1的位数 {
    public class Solution {

        public int NumberOf1(int n) {
            int count = 0;
            int flag = 1;
            while (flag != 0) {
                if ((n & flag) != 0) {
                    ++count;
                }
                flag = flag << 1;
            }
            return count;
        }

        public int NumberOf2(int n) {
            int count = 0;
            while (n != 0) {
                ++count;
                n = n & (n - 1);
            }
            return count;
        }
    }
}
