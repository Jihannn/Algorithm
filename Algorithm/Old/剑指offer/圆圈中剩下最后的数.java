package com.jihan.algorithm.剑指offer;

/**
 * @author Jihan
 * @date 2019/8/18
 *
 * n个人，从0开始报数，报到(m-1)的那个人退出。
 * 下一个人继续从0开始报数。求胜利者的编号。
 */
public class 圆圈中剩下最后的数 {
    public int LastRemaining_Solution(int n, int m) {
        int[] array = new int[n];
        int cur = -1;
        int step = 0;
        int count = n;
        while (count > 0) {
            ++cur;
            if (cur > n - 1) {
                cur = 0;
            }
            if (array[cur] == -1) {
                continue;
            }
            ++step;
            if (step == m) {
                step = 0;
                array[cur] = -1;
                --count;
            }
        }
        return cur;
    }
}
