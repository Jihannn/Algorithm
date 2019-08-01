package com.jihan.algorithm.剑指offer;

/**
 * Created by Jihan on 2019/8/1
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
public class 跳台阶 {

    public int JumpFloor(int target) {
        if (target <= 0) {
            return 0;
        } else if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        }
        return JumpFloor(target - 1) + JumpFloor(target - 2);
    }

    public int JumpFloor2(int target) {
        if(target == 1){
            return 1;
        }else if(target == 2){
            return 2;
        }
        int first = 1;
        int second = 2;
        int method = 0;
        for(int i = 3; i <= target; i++){
            method = first + second;
            first = second;
            second = method;
        }
        return method;
    }
}