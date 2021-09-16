package com.jihan.algorithm.剑指offer;

/**
 * Created by Jihan on 2019/8/1
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class 变态跳台阶 {

    public int JumpFloorII(int target) {
        if(target <= 0){
            return 0;
        }else if(target == 1){
            return 1;
        }else{
            return 2 * JumpFloorII(target - 1);
        }
    }

}
