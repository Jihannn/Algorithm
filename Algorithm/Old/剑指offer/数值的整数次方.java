package com.jihan.algorithm.剑指offer;

/**
 * Created by Jihan on 2019/8/2
 * 
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 */
public class 数值的整数次方 {

    public class Solution {
        public double Power(double base, int exponent) {
            if (base == 0) {
                return 0;
            }
            if (exponent == 0 || base == 1) {
                return 1;
            }

            double result = 1;
            double cur = base;
            int n = exponent > 0 ? exponent : -exponent;

            while (n != 0) {
                if ((n & 1) == 1) {
                    result *= cur;
                }
                cur *= cur;
                n = n >> 1;
            }
            return exponent > 0 ? result : (1 / result);
        }
    }
}
