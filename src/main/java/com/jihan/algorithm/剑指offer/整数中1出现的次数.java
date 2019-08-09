package com.jihan.algorithm.剑指offer;

/**
 * Created by Jihan on 2019/8/9
 *
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
 */
public class 整数中1出现的次数 {
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        for (int i = 1; i <= n; i *= 10) {
            int a = n / i;
            int b = n % i;
            count += (a + 8) / 10 * i;
            if (a % 10 == 1) {
                count += (b + 1);
            }
        }
        return count;
    }
}
