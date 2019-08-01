package com.jihan.algorithm.剑指offer;

/**
 * Created by Jihan on 2019/8/1
 *
 *大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 */
public class 斐波那契数列 {

    public int Fibonacci1(int n) {
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        return Fibonacci1(n-1) + Fibonacci1(n-2);
    }

    public int Fibonacci(int n) {
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        int first = 0;
        int second = 1;
        int result = 0;
        for(int i = 1 ; i < n; i++){
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }
}
