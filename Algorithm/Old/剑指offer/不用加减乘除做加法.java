package com.jihan.algorithm.剑指offer;

/**
 * @author Jihan
 * @date 2019/8/19
 */
public class 不用加减乘除做加法 {
    public int Add(int num1,int num2) {
        while(num2 != 0){
            //异或为不考虑进位的相加
            int count = num1 ^ num2;
            //与并且左移获得进位
            int carry = (num1 & num2) << 1;
            num1 = count;
            num2 = carry;
        }
        return num1;
    }
}
