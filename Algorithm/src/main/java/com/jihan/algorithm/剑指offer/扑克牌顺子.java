package com.jihan.algorithm.剑指offer;

import java.util.Arrays;

/**
 * @author Jihan
 * @date 2019/8/18
 *
 * 一副扑克牌里面有2个大王,2个小王.
 * 抽五张牌，大小王当0,A当1,J当11,Q当12,K当13.
 * 是否为顺子?
 */
public class 扑克牌顺子 {

    public boolean isContinuous(int [] numbers) {
        int length = numbers.length;
        if(length != 5){
            return false;
        }
        int zeroCount = 0;
        int spaceCount = 0;
        Arrays.sort(numbers);
        for(int i = 0; i < length - 1; i++){
            if(numbers[i] == 0){
                ++zeroCount;
                continue;
            }
            if(numbers[i] < 0 || numbers[i] > 13 || numbers[i] == numbers[i + 1]){
                return false;
            }
            spaceCount += numbers[i+1] - numbers[i] - 1;
        }
        if(zeroCount >= spaceCount){
            return true;
        }
        return false;
    }
}
