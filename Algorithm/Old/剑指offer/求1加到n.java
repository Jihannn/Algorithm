package com.jihan.algorithm.剑指offer;

/**
 * @author Jihan
 * @date 2019/8/19
 *
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class 求1加到n {
    public int Sum_Solution(int n) {
        int i = n;
        boolean end = (n != 0) && (i+= Sum_Solution(n - 1)) != 0;
        return i;
    }
}
